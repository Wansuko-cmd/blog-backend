package repository

import entities.article.Article
import value_object.common.UniqueId

class ArticleRepositoryImpl : ArticleRepository {

    override suspend fun getAll(): List<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: UniqueId): Article {
        TODO("Not yet implemented")
    }
}
