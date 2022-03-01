package entities.article

import state.State
import utils.UniqueId

interface ArticleRepository {

    suspend fun getAll(): State<List<Article>, Exception>

    suspend fun getById(id: UniqueId): State<Article, Exception>

    suspend fun insert(article: Article): State<Unit, Exception>

    suspend fun update(article: Article): State<Unit, Exception>

    suspend fun delete(id: UniqueId): State<Unit, Exception>
}
