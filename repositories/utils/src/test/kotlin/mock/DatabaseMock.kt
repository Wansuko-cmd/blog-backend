package mock

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import databases.DatabaseWrapper
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

val TestDatabase1 by lazy { DatabaseWrapper(connectDatabase(1).apply{ createTable() }) }
val TestDatabase2 by lazy { DatabaseWrapper(connectDatabase(2).apply{ createTable() }) }
val TestDatabase3 by lazy { DatabaseWrapper(connectDatabase(3).apply{ createTable() }) }

private fun connectDatabase(number: Int): Database {

    val config = HikariConfig().apply {
        driverClassName ="org.h2.Driver"
        jdbcUrl = "jdbc:h2:mem:test_db${number};DB_CLOSE_DELAY=-1"
        username = ""
        password = ""
        maximumPoolSize = 3
        isAutoCommit = false
    }
//    return Database.connect(HikariDataSource(config))
    return Database.connect(
        "jdbc:h2:mem:test_db${number};DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = "",
        setupConnection = { it.autoCommit = false },
        databaseConfig = DatabaseConfig {
            useNestedTransactions = true
        }
    )
}

private fun Database.createTable() {
    transaction(this) { SchemaUtils.create(TestDomains) }
}
