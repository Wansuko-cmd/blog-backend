package external

import entities.article.Article
import kotlinx.datetime.LocalDateTime
import value_object.article.ArticleBody
import value_object.article.ArticleTitle
import value_object.article.GoodCount
import value_object.common.UniqueId

data class ExternalArticle(
    val id: String,
    val title: String,
    val body: String,
    val goodCount: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
) {
    companion object {
        fun fromArticle(article: Article) = ExternalArticle(
            id = article.id.value,
            title = article.title.value,
            body = article.body.value,
            goodCount = article.goodCount.value,
            createdAt = article.createdAt,
            modifiedAt = article.modifiedAt,
        )

        fun toArticle(externalArticle: ExternalArticle) = Article(
            id = UniqueId(externalArticle.id),
            title = ArticleTitle(externalArticle.title),
            body = ArticleBody(externalArticle.body),
            goodCount = GoodCount(externalArticle.goodCount),
            createdAt = externalArticle.createdAt,
            modifiedAt = externalArticle.modifiedAt,
        )
    }
}
