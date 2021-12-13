package core

import enum.IsSuccess
import external.ExternalArticle

interface ArticleService {

    suspend fun getAll(): List<ExternalArticle>

    suspend fun getWithPaginate(page: Int, offset: Int): List<ExternalArticle>

    suspend fun getById(id: String): ExternalArticle

    suspend fun create(title: String, body: String): IsSuccess

    suspend fun update(id: String, title: String? = null, body: String? = null, goodCount: Int? = null): IsSuccess

    suspend fun delete(id: String): IsSuccess
}
