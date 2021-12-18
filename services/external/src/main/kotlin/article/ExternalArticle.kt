package article

import entities.article.Article
import kotlinx.datetime.LocalDateTime

data class ExternalArticle(
    val id: String,
    val title: String,
    val body: String,
    val goodCount: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
)


fun Article.toExternalArticle(): ExternalArticle = ExternalArticle(
    id = id.value,
    title = title.value,
    body = body.value,
    goodCount = goodCount.value,
    createdAt = createdAt,
    modifiedAt = modifiedAt,
)
