package entities.comment

import exceptions.CreateDataFailedException
import exceptions.GetDataFailedException
import state.State
import utils.UniqueId

interface CommentRepository {
    suspend fun getAllByArticleId(articleId: UniqueId): State<List<Comment>, GetDataFailedException>

    suspend fun insert(comment: Comment): State<UniqueId, CreateDataFailedException>
}
