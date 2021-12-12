package core

import entities.article.Article
import enum.IsSuccess
import external.ExternalArticle
import external.toExternalArticle
import handler.readErrorHandler
import handler.writeErrorHandler
import repository.ArticleRepository
import value_objects.article.ArticleBody
import value_objects.article.ArticleTitle
import value_objects.article.GoodCount
import value_objects.common.UniqueId

class ArticleServiceImpl(private val repository: ArticleRepository) : ArticleService {

    override suspend fun getAll(): List<ExternalArticle> = readErrorHandler {
        repository.getAll()
    }

    override suspend fun getById(id: String): ExternalArticle = readErrorHandler {
        repository.getById(id)
    }

    override suspend fun create(title: String, body: String): IsSuccess = writeErrorHandler {
        val articleTitle = ArticleTitle(title)
        val articleBody = ArticleBody(body)

        val article = Article(title = articleTitle, body = articleBody)
        repository.insert(article.toExternalArticle())
    }

    override suspend fun update(
        id: String,
        title: String?,
        body: String?,
        goodCount: Int?
    ): IsSuccess = writeErrorHandler {

        val oldArticle = repository.getById(id)

        val articleTitle = ArticleTitle(title ?: oldArticle.title)
        val articleBody = ArticleBody(body ?: oldArticle.body)
        val articleGoodCount = GoodCount(goodCount ?: oldArticle.goodCount)

        val newArticle = Article(
            title = articleTitle,
            body = articleBody,
            goodCount = articleGoodCount,
            createdAt = oldArticle.createdAt,
            id = UniqueId(oldArticle.id)
        )

        repository.update(newArticle.toExternalArticle())
    }

    override suspend fun delete(id: String): IsSuccess = writeErrorHandler {
        val uniqueId = UniqueId(id)

        repository.delete(uniqueId.value)
    }
}
