package boundary

import kotlinx.datetime.LocalDateTime

data class ExternalArticle(
    val id: String,
    val title: String,
    val body: String,
    val goodCount: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
)
