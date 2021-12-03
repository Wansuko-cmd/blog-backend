package core

import entities.article.Article
import enum.IsSuccess
import handler.readErrorHandler
import repository.ArticleRepository
import value_object.common.UniqueId

class ArticleServiceImpl(private val repository: ArticleRepository) : ArticleService {

    override suspend fun getAll(): List<Article> = readErrorHandler {
        repository.getAll()
    }

    override suspend fun getById(id: UniqueId): Article = readErrorHandler {
        repository.getById(id)
    }

    override suspend fun create(article: Article): IsSuccess {
        TODO("Not yet implemented")
    }

    override suspend fun update(article: Article): IsSuccess {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: UniqueId): IsSuccess {
        TODO("Not yet implemented")
    }
}
