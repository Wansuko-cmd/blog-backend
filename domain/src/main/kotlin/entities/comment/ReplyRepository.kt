package entities.comment

import exceptions.CreateDataFailedException
import exceptions.GetDataFailedException
import state.State
import utils.UniqueId

interface ReplyRepository {
    suspend fun getAllByCommentId(commentId: UniqueId): State<List<Reply>, GetDataFailedException>

    suspend fun insert(reply: Reply): State<UniqueId, CreateDataFailedException>
}
