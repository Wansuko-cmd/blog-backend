package db.builder

import databases.DatabaseWrapper
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Articles

val H2: DatabaseWrapper by lazy {
    DatabaseWrapper(connectDatabase().apply { createTable() })
}

private fun connectDatabase(): Database {
    return Database.connect(
        "jdbc:h2:mem:product_db;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = ""
    )
}

private fun Database.createTable() {
    transaction(this) {
        SchemaUtils.create(Articles)
    }
}

private fun Database.seeding(database: Database, vararg tables: Table) {
    for (table in tables) {
        val data = transaction(database) { table.selectAll() }
        transaction(this) { table.insert(data) }
    }
}
