@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target.article_repository_impl.update

import article.ExternalArticle
import dsl.ArticleDslImpl
import kotlinx.coroutines.runBlocking
import mock.*
import repository.ArticleRepositoryImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class ArticleRepositoryImplUpdateTest {

    private val articleRepository = ArticleRepositoryImpl(ArticleDslImpl(), TestDatabaseForUpdate)

    @Test
    fun updateでレコードを更新する(): Unit = runBlocking {

        val oldRecord = TestData.first()
        val newRecord = ExternalArticle(oldRecord.id, "NewTitle", "NewBody", 0, time, time)

        val result = articleRepository.update(newRecord)

        assertEquals(Unit, result)
        assertEquals(newRecord, articleRepository.getById(oldRecord.id))
    }
}
