package entities.comment

import api.now
import kotlinx.datetime.LocalDateTime
import utils.UniqueId

data class Comment(
    val id: UniqueId = UniqueId(),
    val articleId: UniqueId,
    val body: CommentBody,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime = LocalDateTime.now(),
)

data class Reply(
    val id: UniqueId = UniqueId(),
    val commentId: UniqueId,
    val body: CommentBody,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime = LocalDateTime.now(),
)

data class CommentBody(val value: String)
