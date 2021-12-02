package core

import entities.article.Article
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
}
