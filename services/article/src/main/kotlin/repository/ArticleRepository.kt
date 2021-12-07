package repository

import external.ExternalArticle

interface ArticleRepository {

    suspend fun getAll(): List<ExternalArticle>

    suspend fun getById(id: String): ExternalArticle
}
