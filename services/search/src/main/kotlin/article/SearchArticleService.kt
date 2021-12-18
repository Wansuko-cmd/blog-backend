package article

interface SearchArticleService {
    suspend fun getAll(): List<ExternalArticle>
    suspend fun getWithPaginate(page: Int, offset: Int): List<ExternalArticle>
    suspend fun getById(id: String): ExternalArticle
}
