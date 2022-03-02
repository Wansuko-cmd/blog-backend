package article

import entities.article.Article
import kotlinx.datetime.LocalDateTime

data class ArticleUseCaseModel(
    val id: String,
    val thumbnailPath: String?,
    val title: String,
    val body: String,
    val goodCount: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
) {
    companion object {
        fun Article.toUseCaseModel() = ArticleUseCaseModel(
            id = id.value,
            thumbnailPath = thumbnailPath?.value,
            title = title.value,
            body = body.value,
            goodCount = goodCount.value,
            createdAt = createdAt,
            modifiedAt = modifiedAt
        )
    }
}
