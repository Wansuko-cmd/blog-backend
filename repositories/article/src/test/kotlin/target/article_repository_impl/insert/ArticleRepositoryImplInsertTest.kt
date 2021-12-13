@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target.article_repository_impl.insert

import dsl.ArticleDslImpl
import enum.IsSuccess
import external.ExternalArticle
import kotlinx.coroutines.runBlocking
import mock.*
import repository.ArticleRepositoryImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class ArticleRepositoryImplInsertTest {

    private val articleRepository = ArticleRepositoryImpl(ArticleDslImpl(), TestDatabaseForInsert)

    @Test
    fun insertで新たなレコードを挿入する(): Unit = runBlocking {

        val record = ExternalArticle("NewId", "NewTitle", "NewBody", 0, time, time)

        val result = articleRepository.insert(record)

        assertEquals(IsSuccess.Success, result)
        assertEquals(record, articleRepository.getById(record.id))
    }
}
