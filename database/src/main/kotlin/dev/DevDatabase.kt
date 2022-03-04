package dev

import DatabaseWrapper
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import db.migrate
import db.seeding
import org.jetbrains.exposed.sql.Database

val DevDatabase: DatabaseWrapper by lazy {

    val pool = hikari()
    Database.connect(pool)
        .also { pool.migrate() }
        .apply { seeding() }
        .let { DatabaseWrapper(it) }
}



private fun hikari(): HikariDataSource {
    val config = HikariConfig().apply {
        driverClassName = "org.h2.Driver"
        jdbcUrl = "jdbc:h2:mem:dev_db;DB_CLOSE_DELAY=-1"
        username = ""
        password = ""
        maximumPoolSize = 3
        isAutoCommit = false
        validate()
    }
    return HikariDataSource(config)
}
