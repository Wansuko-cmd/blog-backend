package comment.create

import comment.CommentUseCaseModel
import exceptions.CreateDataFailedException
import state.State

interface CreateCommentUseCase {
    suspend fun create(
        articleId: String,
        body: String,
    ): State<CommentUseCaseModel, CreateDataFailedException>
}
