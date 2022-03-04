@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package com.wsr.integration.articles.get

import article.ArticleUseCaseModel.Companion.toUseCaseModel
import com.wsr.integration.engine
import com.wsr.routing.articles.ArticleSerializable.Companion.toSerializable
import com.wsr.integration.testdb.seeding.TestArticleModelSeeder
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ArticleGetTest {

    @Test
    fun 全ての記事を取得する() {
        with(engine) {
            handleRequest(HttpMethod.Get, "/articles").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(
                    expected = TestArticleModelSeeder.articleData
                        .map { it.toUseCaseModel().toSerializable() }
                        .let { Json.encodeToString(it) },
                    actual = response.content,
                )
            }
        }
    }

    @Test
    fun 特定の記事を取得する() {
        val target = TestArticleModelSeeder.articleData.first()
        with(engine) {
            handleRequest(HttpMethod.Get, "/articles/${target.id.value}").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(
                    expected = target.toUseCaseModel()
                        .toSerializable()
                        .let { Json.encodeToString(it) },
                    actual = response.content
                )
            }
        }
    }

    @Test
    fun 存在しない記事IDを渡されたらNotFoundを返す() {
        with(engine) {
            handleRequest(HttpMethod.Get, "/articles/no-exist-article-id").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
            }
        }
    }
}
