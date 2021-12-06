package handler

import exceptions.ServiceException
import logger.errorLog
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.name
import org.jetbrains.exposed.sql.transactions.transaction

inline fun <TClass: Any, Result> TClass.readDatabaseHandler(
    vararg databases: Database,
    block: (Database) -> Result
): Result {

    for (database in databases) {
        try {
            return block(database)
        } catch (e: NoSuchElementException) {
            throw ServiceException.NotFoundException()
        } catch (e: Exception) {
            errorLog(e, "読み取り失敗", mapOf("database" to database.name, "repository" to javaClass.simpleName))
        }
    }


    val exception = ServiceException.DatabaseErrorException()
    errorLog(exception, "全てのデータベースで読み取り失敗", mapOf("repository" to javaClass.simpleName))
    throw exception
}

inline fun <TClass> TClass.writeDatabasesHandler(vararg databases: Database, crossinline block: Database.() -> Unit) {
    try {
        transaction { databases.forEach(block) }
    } catch (e: Exception) {
        throw Exception()
    }
}
