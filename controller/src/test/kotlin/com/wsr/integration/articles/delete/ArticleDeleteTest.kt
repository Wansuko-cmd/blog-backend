@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package com.wsr.integration.articles.delete

import com.wsr.integration.testEngine
import com.wsr.integration.testdb.seeding.TestArticleModelSeeder
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ArticleDeleteTest {

    @Test
    fun 特定の記事を削除する() {
        val target = TestArticleModelSeeder.articleData.last()

        with(testEngine) {
            handleRequest(HttpMethod.Delete, "/articles/${target.id.value}").apply {
                assertEquals(HttpStatusCode.OK, response.status())

                handleRequest(HttpMethod.Get, "/articles/${target.id.value}").apply {
                    assertEquals(HttpStatusCode.NotFound, response.status())
                }
            }
        }
    }
}
