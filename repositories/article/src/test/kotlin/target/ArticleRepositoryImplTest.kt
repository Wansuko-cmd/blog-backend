@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target

import dsl.ArticleDslImpl
import exceptions.ServiceException
import kotlinx.coroutines.runBlocking
import mock.TestDatabase
import mock.articleTestData
import repository.ArticleRepositoryImpl
import value_object.common.UniqueId
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
        val uniqueId = UniqueId("UniqueId2")
        val article = articleRepository.getById(uniqueId)
        assertEquals(articleTestData.first { it.id == uniqueId }, article)
    }

    @Test
    fun 見つからなければNotFoundExceptionを出す() = runBlocking {
        val uniqueId = UniqueId("Not exist id")
        assertFailsWith<ServiceException.NotFoundException> { articleRepository.getById(uniqueId) }

        return@runBlocking
    }
}
