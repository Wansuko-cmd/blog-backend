@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package com.wsr.integration.articles.update

import com.wsr.integration.testEngine
import com.wsr.integration.testdb.seeding.TestArticleModelSeeder
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ArticleUpdateTest {

    private val testJson = """
        {
            "thumbnail_path" : null,
            "title" : "TestTitle",
            "body" : "TestBody"
        }
    """.trimIndent()

    @Test
    fun 特定の記事を更新する() {
        val target = TestArticleModelSeeder.articleData.first()

        with(testEngine) {
            handleRequest(HttpMethod.Put, "/articles/${target.id.value}") {
                addHeader("Content-Type", ContentType.Application.Json.toString())
                setBody(testJson)
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())

                val updateResponseContent = response.content

                handleRequest(HttpMethod.Get, "/articles/${target.id.value}").apply {
                    assertEquals(
                        expected = response.content,
                        actual = updateResponseContent
                    )
                }
            }

        }
    }

    @Test
    fun 特定の記事のGoodの数を1増やす() {
        val target = TestArticleModelSeeder.articleData.first()

        with(testEngine) {
            handleRequest(HttpMethod.Put, "/articles/${target.id.value}/good-count").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun 存在しない記事を更新する場合NotFound() {
        with(testEngine) {
            handleRequest(HttpMethod.Put, "/articles/notExistId"){
                addHeader("Content-Type", ContentType.Application.Json.toString())
                setBody(testJson)
            }.apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
            }
        }
    }
}
