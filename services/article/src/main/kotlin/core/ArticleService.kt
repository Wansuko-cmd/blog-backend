package core

import entities.article.Article
import enum.IsSuccess
import value_object.common.UniqueId

interface ArticleService {

    suspend fun getAll(): List<Article>

    suspend fun getById(id: UniqueId): Article

    suspend fun create(article: Article): IsSuccess

    suspend fun update(article: Article): IsSuccess

    suspend fun delete(id: UniqueId): IsSuccess
}
