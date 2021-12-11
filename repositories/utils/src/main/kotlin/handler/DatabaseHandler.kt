package handler

import databases.DatabaseWrapper
import enum.IsSuccess
import exceptions.ServiceException
import logger.errorLog
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.name
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
                    "repository" to this.javaClass.simpleName,
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
    block: (Database) -> Unit
): IsSuccess {
    try {
        transaction { databases.forEach { block(it.instance) } }

        return IsSuccess.Success
    } catch (e: Exception) {
        errorLog(e, "データベースの書き込み失敗", mapOf(
            "repository" to this.javaClass.simpleName,
            "block" to block.javaClass.toString(),
        ))
        throw ServiceException.DatabaseErrorException()
    }
}
