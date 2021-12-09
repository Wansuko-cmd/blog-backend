package core

import entities.article.Article
import enum.IsSuccess
import external.ExternalArticle
import external.toExternalArticle
import handler.readErrorHandler
import handler.writeErrorHandler
import repository.ArticleRepository
import value_object.article.ArticleBody
import value_object.article.ArticleTitle

class ArticleServiceImpl(private val repository: ArticleRepository) : ArticleService {

    override suspend fun getAll(): List<ExternalArticle> = readErrorHandler {
        repository.getAll()
    }

    override suspend fun getById(id: String): ExternalArticle = readErrorHandler {
        repository.getById(id)
    }

    override suspend fun create(title: String, body: String): IsSuccess = writeErrorHandler{
        val articleTitle = ArticleTitle(title)
        val articleBody = ArticleBody(body)

        val article = Article(articleTitle, articleBody)
        repository.insert(article.toExternalArticle())
    }

    override suspend fun update(title: String, body: String, goodCount: Int): IsSuccess {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): IsSuccess {
        TODO("Not yet implemented")
    }
}
