package core

import entities.article.Article
import value_object.common.PrimaryKey

interface ArticleService {

    suspend fun getAll(): List<Article>

    suspend fun getById(id: PrimaryKey): Article
}
