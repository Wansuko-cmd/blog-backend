package mock

import external.ExternalArticle
import databases.DatabaseWrapper
import kotlinx.datetime.toJavaLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Articles

val TestDatabase by lazy {
    DatabaseWrapper(
        connectDatabase().apply {
            createTable()
            seeding(articleTestData)
        }
    )
}

private fun connectDatabase(): Database {
    return Database.connect(
        "jdbc:h2:mem:test_db;DB_CLOSE_DELAY=-1",
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

private fun Database.seeding(articles: List<ExternalArticle>) {
    transaction(this) {
        articles.map { externalArticle ->
            Articles.insert {
                it[id] = externalArticle.id
                it[title] = externalArticle.title
                it[body] = externalArticle.body
                it[goodCount] = externalArticle.goodCount
                it[createdAt] = externalArticle.createdAt.toJavaLocalDateTime()
                it[modifiedAt] = externalArticle.modifiedAt.toJavaLocalDateTime()
            }
        }
    }
}
