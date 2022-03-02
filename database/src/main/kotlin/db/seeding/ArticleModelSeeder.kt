package db.seeding

import entities.article.Article
import entities.article.ArticleBody
import entities.article.ArticleTitle
import entities.article.GoodCount
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction
import table.ArticleModel
import utils.ImagePath
import utils.UniqueId

object ArticleModelSeeder : DatabaseSeeder {
    override fun seeding(database: Database) {
        transaction(database) {
            ArticleModel.batchInsert(articleTestData) {
                this[ArticleModel.id] = it.id.value
                this[ArticleModel.title] = it.title.value
                this[ArticleModel.body] = it.body.value
                this[ArticleModel.goodCount] = it.goodCount.value
                this[ArticleModel.createdAt] = it.createdAt.toJavaLocalDateTime()
                this[ArticleModel.modifiedAt] = it.modifiedAt.toJavaLocalDateTime()
            }
        }
    }

    private val time = LocalDateTime.parse("2005-05-05T15:00:00")

    private val articleTestData = listOf(
        Article(
            UniqueId("UniqueId1"),
            ImagePath("Thumbnail1"),
            ArticleTitle("Title1"),
            ArticleBody("Body1"),
            GoodCount(1),
            time,
            time,
        ),
        Article(
            UniqueId("UniqueId2"),
            null,
            ArticleTitle("Title2"),
            ArticleBody("Body2"),
            GoodCount(2),
            time,
            time,
        ),
        Article(
            UniqueId("UniqueId3"),
            ImagePath("Thumbnail3"),
            ArticleTitle("Title3"),
            ArticleBody("Body3"),
            GoodCount(3),
            time,
            time,
        ),
        Article(
            UniqueId("UniqueId4"),
            ImagePath("Thumbnail4"),
            ArticleTitle("Title4"),
            ArticleBody("Body4"),
            GoodCount(4),
            time,
            time,
        )
    )
}
