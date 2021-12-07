package repository

import boundary.ExternalArticle

interface ArticleRepository {

    suspend fun getAll(): List<ExternalArticle>

    suspend fun getById(id: String): ExternalArticle
}
