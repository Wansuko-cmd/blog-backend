package entities.article

import exceptions.CreateDataFailedException
import exceptions.DeleteDataFailedException
import exceptions.GetDataFailedException
import exceptions.UpdateDataFailedException
import state.State
import utils.UniqueId

interface ArticleRepository {

    suspend fun getAll(): State<List<Article>, GetDataFailedException>

    suspend fun getById(id: UniqueId): State<Article, GetDataFailedException>

    suspend fun insert(article: Article): State<UniqueId, CreateDataFailedException>

    suspend fun update(article: Article): State<UniqueId, UpdateDataFailedException>

    suspend fun delete(id: UniqueId): State<UniqueId, DeleteDataFailedException>
}
