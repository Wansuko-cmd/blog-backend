@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target.article_repository_impl.delete

import dsl.ArticleDslImpl
import exceptions.RepositoryException
import kotlinx.coroutines.runBlocking
import mock.*
import repository.ArticleRepositoryImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ArticleRepositoryImplDeleteTest {

    private val articleRepository = ArticleRepositoryImpl(ArticleDslImpl(), TestDatabaseForDelete)

    @Test
    fun deleteで指定されたレコードを削除する(): Unit = runBlocking {
        val targetRecord = TestData.first()

        val result = articleRepository.delete(targetRecord.id)

        assertEquals(Unit, result)
        assertFailsWith<RepositoryException.NotFoundException> { articleRepository.getById(targetRecord.id) }
    }
}
