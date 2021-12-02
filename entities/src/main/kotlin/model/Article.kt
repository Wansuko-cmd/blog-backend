package model

import kotlinx.datetime.LocalDateTime

data class Article(
    val id: String,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
)
