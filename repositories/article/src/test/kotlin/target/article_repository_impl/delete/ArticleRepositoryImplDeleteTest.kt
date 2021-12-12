@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target.article_repository_impl.delete

import dsl.ArticleDslImpl
import enum.IsSuccess
import exceptions.RepositoryException
import kotlinx.coroutines.runBlocking
import mock.TestData
import mock.TestDatabase
import repository.ArticleRepositoryImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ArticleRepositoryImplDeleteTest {

    private val articleRepository = ArticleRepositoryImpl(ArticleDslImpl(), TestDatabase)

    @Test
    fun deleteで指定されたレコードを削除する(): Unit = runBlocking {
        val targetRecord = TestData.first()

        val result = articleRepository.delete(targetRecord.id)

        assertEquals(IsSuccess.Success, result)
        assertFailsWith<RepositoryException.NotFoundException> { articleRepository.getById(targetRecord.id) }
    }
}
