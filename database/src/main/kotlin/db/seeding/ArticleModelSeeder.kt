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
            ArticleModel.batchInsert(articleData) {
                this[ArticleModel.id] = it.id.value
                this[ArticleModel.thumbnailPath] = it.thumbnailPath?.value
                this[ArticleModel.title] = it.title.value
                this[ArticleModel.body] = it.body.value
                this[ArticleModel.goodCount] = it.goodCount.value
                this[ArticleModel.createdAt] = it.createdAt.toJavaLocalDateTime()
                this[ArticleModel.modifiedAt] = it.modifiedAt.toJavaLocalDateTime()
            }
        }
    }

    private val time = LocalDateTime.parse("2005-05-05T15:00:00")

    private val articleData = listOf(
        Article(
            id = UniqueId("articleId1"),
            thumbnailPath = ImagePath("Thumbnail1"),
            title = ArticleTitle("Title1"),
            body = ArticleBody("Body1"),
            goodCount = GoodCount(1),
            createdAt = time,
            modifiedAt = time,
        ),
        Article(
            id = UniqueId("articleId2"),
            thumbnailPath = null,
            title = ArticleTitle("Title2"),
            body = ArticleBody("Body2"),
            goodCount = GoodCount(2),
            createdAt = time,
            modifiedAt = time,
        ),
        Article(
            id = UniqueId("articleId3"),
            thumbnailPath = ImagePath("Thumbnail3"),
            title = ArticleTitle("Title3"),
            body = ArticleBody("Body3"),
            goodCount = GoodCount(3),
            createdAt = time,
            modifiedAt = time,
        ),
        Article(
            id = UniqueId("articleId4"),
            thumbnailPath = ImagePath("Thumbnail4"),
            title = ArticleTitle("Title4"),
            body = ArticleBody("Body4"),
            goodCount = GoodCount(4),
            createdAt = time,
            modifiedAt = time,
        )
    )
}
