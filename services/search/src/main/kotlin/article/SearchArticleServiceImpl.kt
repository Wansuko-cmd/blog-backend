package article

import handler.readErrorHandler

class SearchArticleServiceImpl(private val repository: ArticleRepository) : SearchArticleService {

    override suspend fun getAll(): List<ExternalArticle> = readErrorHandler {
        repository.getAll().sortedBy { it.createdAt }
    }

    override suspend fun getWithPaginate(page: Int, offset: Int): List<ExternalArticle> = readErrorHandler {
        repository.getAll()
            .sortedBy { it.createdAt }
            .chunked(offset)
            .elementAtOrNull(page - 1) ?: listOf()
    }

    override suspend fun getById(id: String): ExternalArticle = readErrorHandler {
        repository.getById(id)
    }
}
