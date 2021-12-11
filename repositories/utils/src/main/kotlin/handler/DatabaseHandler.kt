package handler

import databases.DatabaseWrapper
import enum.IsSuccess
import exceptions.ServiceException
import logger.errorLog
import logger.infoLog
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.withDataBaseLock
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.name
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

fun <TClass: Any, Result> TClass.readDatabaseHandler(
    vararg databases: DatabaseWrapper,
    block: (Database) -> Result
): Result {

    for (database in databases) {
        try {
            return block(database.instance)
        } catch (e: NoSuchElementException) {
            throw ServiceException.NotFoundException()
        } catch (e: Exception) {
            errorLog(
                e,
                "読み取り失敗",
                mapOf(
                    "database" to database.instance.name,
                    "repository" to javaClass.simpleName,
                    "block" to block.javaClass.toString(),
                )
            )
        }
    }

    val exception = ServiceException.DatabaseErrorException()
    errorLog(exception, "全てのデータベースで読み取り失敗", mapOf("repository" to javaClass.simpleName))
    throw exception
}

fun <TClass: Any> TClass.writeDatabasesHandler(
    vararg databases: DatabaseWrapper,
    block: (Database) -> Transaction
): IsSuccess {
    val transactions = mutableListOf<Transaction>()

    return try {
        databases.forEach { transactions.add(block(it.instance)) }

        transactions.forEach { transaction ->
            val currentOrNull = TransactionManager.Companion::class.java.getMethod("currentOrNull")
            currentOrNull.invoke(TransactionManager.Companion)
            transaction.commit()
        }

        IsSuccess.Success

    } catch (e: Exception) {

        errorLog(e, "データベースの書き込み失敗", mapOf(
            "repository" to javaClass.simpleName,
            "block" to block.javaClass.toString(),
        ))

        transactions.forEach { it.rollback() }

        infoLog("データベースの書き込みのロールバック完了", mapOf("repository" to javaClass.simpleName))

        throw ServiceException.DatabaseErrorException()
    }
}
