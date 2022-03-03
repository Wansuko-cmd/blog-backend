package comment.create

import comment.ReplyUseCaseModel
import comment.ReplyUseCaseModel.Companion.toUseCaseModel
import entities.comment.CommentBody
import entities.comment.Reply
import entities.comment.ReplyRepository
import exceptions.CreateDataFailedException
import state.State
import state.onSuccess
import utils.UniqueId

class CreateReplyUseCaseImpl(
    private val replyRepository: ReplyRepository,
) : CreateReplyUseCase {
    override suspend fun create(
        commentId: String,
        body: String
    ): State<ReplyUseCaseModel, CreateDataFailedException> {
        val reply = Reply(
            commentId = UniqueId(commentId),
            body = CommentBody(body),
        )
        return replyRepository.insert(reply)
            .onSuccess { reply.toUseCaseModel() }
    }
}
