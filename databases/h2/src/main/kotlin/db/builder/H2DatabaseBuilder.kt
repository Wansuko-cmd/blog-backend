package db.builder

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Articles

val H2: Database by lazy {
    connectDatabase().apply { createTable() }
}

private fun connectDatabase(): Database{
    return Database.connect(
        "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = ""
    )
}

private fun Database.createTable(){
    transaction(this) {
        SchemaUtils.create(Articles)
    }
}
