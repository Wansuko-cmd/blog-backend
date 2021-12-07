package mock

import databases.DatabaseWrapper
import entities.article.Article
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

private fun Database.seeding(articles: List<Article>) {
    transaction(this) {
        articles.map { article ->
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
