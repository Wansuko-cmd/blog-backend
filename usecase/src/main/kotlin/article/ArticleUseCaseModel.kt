package article

import entities.article.Article
import entities.article.ArticleBody
import entities.article.ArticleTitle
import entities.article.GoodCount
import kotlinx.datetime.LocalDateTime
import utils.UniqueId

data class ArticleUseCaseModel(
    val id: String,
    val title: String,
    val body: String,
    val goodCount: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
) {
    companion object {
        fun Article.toUseCaseModel() = ArticleUseCaseModel(
            id = id.value,
            title = title.value,
            body = body.value,
            goodCount = goodCount.value,
            createdAt = createdAt,
            modifiedAt = modifiedAt
        )

        fun ArticleUseCaseModel.toArticle() = Article(
            id = UniqueId(id),
            title = ArticleTitle(title),
            body = ArticleBody(body),
            goodCount = GoodCount(goodCount),
            createdAt = createdAt,
            modifiedAt = modifiedAt
        )
    }
}
