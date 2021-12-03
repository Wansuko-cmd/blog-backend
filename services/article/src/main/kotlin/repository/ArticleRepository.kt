package repository

import entities.article.Article
import value_object.common.UniqueId

interface ArticleRepository {

    suspend fun getAll(): List<Article>

    suspend fun getById(id: UniqueId): Article
}
