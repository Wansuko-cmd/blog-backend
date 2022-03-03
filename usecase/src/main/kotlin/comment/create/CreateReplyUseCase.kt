package comment.create

import comment.ReplyUseCaseModel
import exceptions.CreateDataFailedException
import state.State

interface CreateReplyUseCase {
    suspend fun create(
        commentId: String,
        body: String,
    ): State<ReplyUseCaseModel, CreateDataFailedException>
}
