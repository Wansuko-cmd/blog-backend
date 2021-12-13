package mock

import databases.DatabaseWrapper
import dsl.ArticleDslImpl
import external.ExternalArticle
import kotlinx.datetime.toJavaLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import repository.ArticleRepositoryImpl
import tables.Articles

val TestDatabaseForGet = DatabaseWrapper(
    connectDatabase("get").apply {
        createTable()
        seeding(TestData)
    }
)

val TestDatabaseForDelete = DatabaseWrapper(
    connectDatabase("delete").apply {
        createTable()
        seeding(TestData)
    }
)

val TestDatabaseForInsert = DatabaseWrapper(
    connectDatabase("insert").apply {
        createTable()
        seeding(TestData)
    }
)

val TestDatabaseForUpdate = DatabaseWrapper(
    connectDatabase("update").apply {
        createTable()
        seeding(TestData)
    }
)

fun connectDatabase(name: String): Database {
    return Database.connect(
        "jdbc:h2:mem:repositories_article_test_db_$name;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver",
        user = "",
        password = ""
    )
}

fun Database.createTable() {
    transaction(this) {
        SchemaUtils.create(Articles)
    }
}

fun Database.seeding(articles: List<ExternalArticle>) {
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
