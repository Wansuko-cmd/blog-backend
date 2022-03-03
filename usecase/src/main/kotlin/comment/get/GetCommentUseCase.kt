package comment.get

import comment.CommentUseCaseModel
import exceptions.GetDataFailedException
import state.State

interface GetCommentUseCase {
    suspend fun getAllByArticleId(articleId: String): State<List<CommentUseCaseModel>, GetDataFailedException>
}
