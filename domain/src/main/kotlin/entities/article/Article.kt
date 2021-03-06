package entities.article

import api.now
import kotlinx.datetime.LocalDateTime
import utils.ImagePath
import utils.UniqueId

data class Article(
    val id: UniqueId = UniqueId(),
    val thumbnailPath: ImagePath?,
    val title: ArticleTitle,
    val body: ArticleBody,
    val goodCount: GoodCount = GoodCount(0),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime = LocalDateTime.now(),
) {
    fun modify(
        thumbnailPath: ImagePath?,
        title: ArticleTitle? = null,
        body: ArticleBody? = null,
    ) = this.copy(
        thumbnailPath = thumbnailPath,
        title = title ?: this.title,
        body = body ?: this.body,
        modifiedAt = LocalDateTime.now()
    )

    fun modify(
        goodCount: GoodCount
    ) = this.copy(
        goodCount = goodCount,
        modifiedAt = LocalDateTime.now(),
    )
}

data class ArticleTitle(val value: String)

data class ArticleBody(val value: String)

data class GoodCount(val value: Int = 0) {
    fun add() = GoodCount(value + 1)
}
