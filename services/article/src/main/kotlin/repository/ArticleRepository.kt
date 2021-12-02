package repository

import entities.article.Article
import value_object.common.PrimaryKey

interface ArticleRepository {

    suspend fun getAll(): List<Article>

    suspend fun getById(id: PrimaryKey): Article
}
