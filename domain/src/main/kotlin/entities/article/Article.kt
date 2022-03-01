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
)

data class ArticleTitle(val value: String)

data class ArticleBody(val value: String)

data class GoodCount(val value: Int = 0)
