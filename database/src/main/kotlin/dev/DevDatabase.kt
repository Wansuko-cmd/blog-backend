package dev

import DatabaseWrapper
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import db.seeding.ArticleModelSeeder
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

val DevDatabase: DatabaseWrapper by lazy {
    val pool = hikari()
    val database = Database.connect(pool)

    migrate(pool)
    seeding.forEach { it.seeding(database) }

    DatabaseWrapper(database)
}


val seeding = listOf(ArticleModelSeeder)

private fun migrate(dataSource: DataSource) {
    val flyway = Flyway.configure()
        .dataSource(dataSource)
        .load()

    flyway.info()
    flyway.migrate()
}

private fun hikari(): HikariDataSource {
    val config = HikariConfig().apply {
        driverClassName = "org.h2.Driver"
        jdbcUrl = "jdbc:h2:mem:dev_db;DB_CLOSE_DELAY=-1"
        username = ""
        password = ""
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }
    return HikariDataSource(config)
}
