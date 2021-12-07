@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target

import dsl.ArticleDslImpl
import exceptions.ServiceException
import kotlinx.coroutines.runBlocking
import mock.TestDatabase
import mock.articleTestData
import repository.ArticleRepositoryImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ArticleRepositoryImplTest {

    private val articleRepository = ArticleRepositoryImpl(ArticleDslImpl(), TestDatabase)

    @Test
    fun getAllで全てのArticleを取得する() = runBlocking {
        val articles = articleRepository.getAll()
        assertEquals(articleTestData, articles)
    }

    @Test
    fun getByIdで特定のArticleを取得する() = runBlocking {
        val id = "UniqueId2"
        val article = articleRepository.getById(id)
        assertEquals(articleTestData.first { it.id == id }, article)
    }

    @Test
    fun 見つからなければNotFoundExceptionを出す() = runBlocking {
        val id = "Not exist id"
        assertFailsWith<ServiceException.NotFoundException> { articleRepository.getById(id) }

        return@runBlocking
    }
}
