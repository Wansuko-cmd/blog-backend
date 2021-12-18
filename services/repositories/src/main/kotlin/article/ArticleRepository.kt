package article

interface ArticleRepository {

    suspend fun getAll(): List<ExternalArticle>

    suspend fun getById(id: String): ExternalArticle

    suspend fun insert(externalArticle: ExternalArticle)

    suspend fun update(externalArticle: ExternalArticle)

    suspend fun delete(id: String)
}
