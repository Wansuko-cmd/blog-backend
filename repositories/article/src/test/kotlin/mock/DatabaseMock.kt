package mock

import kotlinx.datetime.toJavaLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Articles

val TestDatabase by lazy {
    connectDatabase().apply {
        createTable()
        seeding()
    }
}

private fun connectDatabase(): Database {
    return Database.connect(
        "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
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

private fun Database.seeding() {
    transaction(this) {
        articleTestData.map { article ->
            Articles.insert {
                it[id] = article.id.value
                it[title] = article.title.value
                it[body] = article.body.value
                it[goodCount] = article.goodCount.value
                it[createdAt] = article.createdAt.toJavaLocalDateTime()
                it[modifiedAt] = article.modifiedAt.toJavaLocalDateTime()
            }
        }
    }
}
