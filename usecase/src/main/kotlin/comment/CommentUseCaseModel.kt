package comment

import comment.ReplyUseCaseModel.Companion.toUseCaseModel
import entities.comment.Comment
import entities.comment.Reply
import kotlinx.datetime.LocalDateTime

data class CommentUseCaseModel(
    val id: String,
    val articleId: String,
    val body: String,
    val reply: List<ReplyUseCaseModel>,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
) {
    companion object {
        fun Pair<Comment, List<Reply>>.toUseCaseModel(): CommentUseCaseModel =
            first.toUseCaseModel(reply = second.map { it.toUseCaseModel() })

        fun Comment.toUseCaseModel(reply: List<ReplyUseCaseModel> = listOf()) = CommentUseCaseModel(
            id = id.value,
            articleId = articleId.value,
            reply = reply,
            body = body.value,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
        )
    }
}

data class ReplyUseCaseModel(
    val id: String,
    val commentId: String,
    val body: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
) {
    companion object {
        fun Reply.toUseCaseModel() = ReplyUseCaseModel(
            id = id.value,
            commentId = commentId.value,
            body = body.value,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
        )
    }
}
