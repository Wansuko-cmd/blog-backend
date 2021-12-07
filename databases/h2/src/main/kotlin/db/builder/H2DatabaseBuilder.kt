package db.builder

import databases.DatabaseWrapper
import db.usedTables
import exceptions.ServiceException
import logger.errorLog
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


@Suppress("FunctionName")
fun H2(database: DatabaseWrapper, tables: List<Table> = usedTables): DatabaseWrapper = try {
    DatabaseWrapper(connectDatabase().apply { setUpDatabase(database, tables) })
} catch (e: Exception) {
    errorLog(e, "H2データベースの初期化に失敗")
    throw ServiceException.DatabaseErrorException()
}

private fun connectDatabase(): Database {
    return Database.connect(
        "jdbc:h2:mem:product_db;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = ""
    )
}

private fun Database.setUpDatabase(database: DatabaseWrapper, tables: List<Table>) {
    createTable(tables)
    copyRecords(database, tables)
}

private fun Database.createTable(tables: List<Table>) {
    transaction(this) {
        tables.forEach { SchemaUtils.create(it) }
    }
}

private fun Database.copyRecords(database: DatabaseWrapper, tables: List<Table>) {

    for (table in tables) {

        //コピー元のデータベースからレコードを取得
        val records = transaction(database.instance) {
            table.selectAll().map { resultRow ->
                table.javaClass
                    .kotlin
                    .objectInstance
                    ?.columns
                    ?.associate { it to resultRow[it] }
                    ?: throw ServiceException.DatabaseErrorException()
            }
        }

        //コピー先のデータベースにレコードを格納
        transaction(this) {
            table.batchInsert(records){ record ->
                record.forEach {
                    @Suppress("UNCHECKED_CAST")
                    this[it.key as Column<Any>] = it.value as Any
                }
            }
        }
    }
}
