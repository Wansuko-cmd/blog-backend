@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target.article_repository_impl.get

import dsl.ArticleDslImpl
import exceptions.RepositoryException
import kotlinx.coroutines.runBlocking
import mock.TestDatabase
import mock.TestData
import mock.seeding
import repository.ArticleRepositoryImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ArticleRepositoryImplGetTest {

    private val articleRepository = ArticleRepositoryImpl(ArticleDslImpl(), TestDatabase)

    @Test
    fun getAllで全てのArticleを取得する() = runBlocking {
        val articles = articleRepository.getAll()
        assertEquals(TestData.sortedBy { it.id }, articles.sortedBy { it.id })
    }

    @Test
    fun getByIdで特定のArticleを取得する() = runBlocking {
        val id = "UniqueId2"
        val article = articleRepository.getById(id)
        assertEquals(TestData.first { it.id == id }, article)
    }

    @Test
    fun 見つからなければNotFoundExceptionを出す() = runBlocking {
        val id = "Not exist id"
        assertFailsWith<RepositoryException.NotFoundException> { articleRepository.getById(id) }

        return@runBlocking
    }
}
