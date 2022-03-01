package entities.article

import api.now
import kotlinx.datetime.LocalDateTime
import utils.UniqueId

data class Article(
    val id: UniqueId = UniqueId(),
    val title: ArticleTitle,
    val body: ArticleBody,
    val goodCount: GoodCount = GoodCount(0),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime = LocalDateTime.now(),
) {
    fun modify(
        title: ArticleTitle? = null,
        body: ArticleBody? = null,
        goodCount: GoodCount? = null,
    ) = Article(
        id = this.id,
        title = title ?: this.title,
        body = body ?: this.body,
        goodCount = goodCount ?: this.goodCount,
        createdAt = createdAt,
        modifiedAt = LocalDateTime.now()
    )
}

data class ArticleTitle(val value: String)

data class ArticleBody(val value: String)

data class GoodCount(val value: Int = 0)
