package article.delete

import exceptions.DeleteDataFailedException
import state.State

interface DeleteArticleUseCase {
    suspend fun delete(id: String): State<Unit, DeleteDataFailedException>
}
