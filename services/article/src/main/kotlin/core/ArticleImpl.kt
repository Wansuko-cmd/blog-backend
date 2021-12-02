package core

import entities.article.Article
import enum.IsSuccess
import handler.readErrorHandler
import repository.ArticleRepository
import value_object.common.PrimaryKey

class ArticleImpl(private val repository: ArticleRepository) : ArticleService {

    override suspend fun getAll(): List<Article> = readErrorHandler {
        repository.getAll()
    }

    override suspend fun getById(id: PrimaryKey): Article = readErrorHandler {
        repository.getById(id)
    }

    override suspend fun create(article: Article): IsSuccess {
        TODO("Not yet implemented")
    }

    override suspend fun update(article: Article): IsSuccess {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: PrimaryKey): IsSuccess {
        TODO("Not yet implemented")
    }
}