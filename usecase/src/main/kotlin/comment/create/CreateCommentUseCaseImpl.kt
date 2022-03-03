package comment.create

import comment.CommentUseCaseModel
import comment.CommentUseCaseModel.Companion.toUseCaseModel
import entities.comment.Comment
import entities.comment.CommentBody
import entities.comment.CommentRepository
import exceptions.CreateDataFailedException
import state.State
import state.onSuccess
import utils.UniqueId

class CreateCommentUseCaseImpl(
    private val commentRepository: CommentRepository,
) : CreateCommentUseCase {
    override suspend fun create(
        articleId: String,
        body: String
    ): State<CommentUseCaseModel, CreateDataFailedException> {
        val comment = Comment(
            articleId = UniqueId(articleId),
            body = CommentBody(body),
        )
        return commentRepository.insert(comment)
            .onSuccess { comment.toUseCaseModel() }
    }
}
