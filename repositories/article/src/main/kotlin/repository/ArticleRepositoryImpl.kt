package repository

import db.builder.H2
import db.handler.readHandler
import dsl.ArticleDslImpl
import entities.article.Article
import value_object.common.UniqueId

class ArticleRepositoryImpl : ArticleRepository {

    private val articleDsl = ArticleDslImpl()

    override suspend fun getAll(): List<Article> = readHandler(H2) {
        articleDsl.getAll(this)
    }

    override suspend fun getById(id: UniqueId): Article = readHandler(H2) {
        articleDsl.getById(this, id)
    }
}
