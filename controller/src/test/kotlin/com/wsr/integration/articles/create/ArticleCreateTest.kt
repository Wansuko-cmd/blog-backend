@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package com.wsr.integration.articles.create

import com.wsr.integration.testEngine
import com.wsr.routing.articles.ArticleSerializable
import com.wsr.routing.articles.create.ArticlesCreateRequest
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class ArticleCreateTest {

    private val testJson = """
        {
            "thumbnail_path" : "TestThumbnail",
            "title" : "TestTitle",
            "body" : "TestBody"
        }
    """.trimIndent()

    @Test
    fun 記事を作成する() {
        with(testEngine) {
            handleRequest(HttpMethod.Post, "/articles") {
                addHeader("Content-Type", ContentType.Application.Json.toString())
                setBody(testJson)
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())

                val sentArticle = Json.decodeFromString<ArticlesCreateRequest>(testJson)
                val responseArticle = Json.decodeFromString<ArticleSerializable>(response.content!!)

                assertEquals(sentArticle.thumbnailPath, responseArticle.thumbnailPath)
                assertEquals(sentArticle.title, responseArticle.title)
                assertEquals(sentArticle.body, responseArticle.body)
            }
        }
    }
}
