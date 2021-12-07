package core

import external.ExternalArticle
import enum.IsSuccess
import handler.readErrorHandler
import repository.ArticleRepository

class ArticleServiceImpl(private val repository: ArticleRepository) : ArticleService {

    override suspend fun getAll(): List<ExternalArticle> = readErrorHandler {
        repository.getAll()
    }

    override suspend fun getById(id: String): ExternalArticle = readErrorHandler {
        repository.getById(id)
    }

    override suspend fun create(article: ExternalArticle): IsSuccess {
        TODO("Not yet implemented")
    }

    override suspend fun update(article: ExternalArticle): IsSuccess {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): IsSuccess {
        TODO("Not yet implemented")
    }
}
