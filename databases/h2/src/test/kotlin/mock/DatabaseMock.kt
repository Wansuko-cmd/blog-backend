package mock

import databases.DatabaseWrapper
import entities.article.Article
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Articles
import value_objects.article.ArticleBody
import value_objects.article.ArticleTitle
import value_objects.article.GoodCount
import value_objects.common.UniqueId

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
        Articles.batchInsert(articles) {
            this[Articles.id] = it.id.value
            this[Articles.title] = it.title.value
            this[Articles.body] = it.body.value
            this[Articles.goodCount] = it.goodCount.value
            this[Articles.createdAt] = it.createdAt.toJavaLocalDateTime()
            this[Articles.modifiedAt] = it.modifiedAt.toJavaLocalDateTime()
        }
    }
}

val time = LocalDateTime.parse("2005-05-05T15:00:00")

val articleTestData = listOf(
    Article(
        ArticleTitle("Title1"),
        ArticleBody("Body1"),
        GoodCount(1),
        time,
        time,
        UniqueId("UniqueId1"),
    ),
    Article(
        ArticleTitle("Title2"),
        ArticleBody("Body2"),
        GoodCount(2),
        time,
        time,
        UniqueId("UniqueId2"),
    ),
    Article(
        ArticleTitle("Title3"),
        ArticleBody("Body3"),
        GoodCount(3),
        time,
        time,
        UniqueId("UniqueId3"),
    ),
    Article(
        ArticleTitle("Title4"),
        ArticleBody("Body4"),
        GoodCount(4),
        time,
        time,
        UniqueId("UniqueId4"),
    )
)
