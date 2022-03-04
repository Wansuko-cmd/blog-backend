package comment.get

import comment.CommentUseCaseModel
import comment.CommentUseCaseModel.Companion.toUseCaseModel
import entities.comment.Comment
import entities.comment.CommentRepository
import entities.comment.Reply
import entities.comment.ReplyRepository
import exceptions.GetDataFailedException
import state.State
import state.flatMap
import state.map
import state.sequence
import utils.UniqueId

class GetCommentUseCaseImpl(
    private val commentRepository: CommentRepository,
    private val replyRepository: ReplyRepository,
) : GetCommentUseCase {
    override suspend fun getAllByArticleId(articleId: String): State<List<CommentUseCaseModel>, GetDataFailedException> =
        commentRepository.getAllByArticleId(UniqueId(articleId))
            .flatMap { comments ->
                comments.map { comment -> comment.withReply().map { it.toUseCaseModel() } }
                    .sequence()
            }

    private suspend fun Comment.withReply(): State<Pair<Comment, List<Reply>>, GetDataFailedException> =
        replyRepository.getAllByCommentId(this.id).map { replies -> this to replies }
}
