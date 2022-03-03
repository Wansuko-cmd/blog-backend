package com.wsr.routing.articles.comments

import com.wsr.routing.articles.comments.ReplySerializable.Companion.toSerializable
import comment.CommentUseCaseModel
import comment.ReplyUseCaseModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentSerializable(
    val id: String,
    @SerialName("article_id") val articleId: String,
    val body: String,
    val reply: List<ReplySerializable>,
    @SerialName("created_at") val createdAt: LocalDateTime,
    @SerialName("modified_at") val modifiedAt: LocalDateTime,
) {
    companion object {
        fun CommentUseCaseModel.toSerializable() = CommentSerializable(
            id = id,
            articleId = articleId,
            body = body,
            reply = reply.map { it.toSerializable() },
            createdAt = createdAt,
            modifiedAt = modifiedAt,
        )
    }
}

@Serializable
data class ReplySerializable(
    val id: String,
    @SerialName("comment_id") val commentId: String,
    val body: String,
    @SerialName("created_at") val createdAt: LocalDateTime,
    @SerialName("modified_at") val modifiedAt: LocalDateTime,
) {
    companion object {
        fun ReplyUseCaseModel.toSerializable() = ReplySerializable(
            id = id,
            commentId = commentId,
            body = body,
            createdAt = createdAt,
            modifiedAt = modifiedAt
        )
    }
}
