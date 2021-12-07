package core

import boundary.ExternalArticle
import enum.IsSuccess

interface ArticleService {

    suspend fun getAll(): List<ExternalArticle>

    suspend fun getById(id: String): ExternalArticle

    suspend fun create(article: ExternalArticle): IsSuccess

    suspend fun update(article: ExternalArticle): IsSuccess

    suspend fun delete(id: String): IsSuccess
}
