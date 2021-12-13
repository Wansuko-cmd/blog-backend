package mock

import databases.DatabaseWrapper
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

val TestDatabase1 by lazy { DatabaseWrapper(connectDatabase().apply { createTable() }) }
val TestDatabase2 by lazy { DatabaseWrapper(connectDatabase().apply { createTable() }) }
val TestDatabase3 by lazy { DatabaseWrapper(connectDatabase().apply { createTable() }) }

var number = 1

private fun connectDatabase(): Database {
    return Database.connect(
        "jdbc:h2:mem:repositories_utils_test_db_${number++};DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = ""
    )
}

private fun Database.createTable() {
    transaction(this) { SchemaUtils.create(TestDomains) }
}
