package repository

import enum.IsSuccess
import external.ExternalArticle

interface ArticleRepository {

    suspend fun getAll(): List<ExternalArticle>

    suspend fun getById(id: String): ExternalArticle

    suspend fun insert(externalArticle: ExternalArticle): IsSuccess

    suspend fun update(externalArticle: ExternalArticle): IsSuccess

    suspend fun delete(id: String): IsSuccess
}
