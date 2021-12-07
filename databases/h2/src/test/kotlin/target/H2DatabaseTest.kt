@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package target

import db.builder.H2
import entities.article.Article
import mock.TestDatabase
import mock.articleTestData
import mock.toArticle
import mock.usedTablesMock
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Articles
import kotlin.test.Test
import kotlin.test.assertEquals

class H2DatabaseTest {

    @Test
    fun 指定したデータベースのレコードをコピーしたH2を返す() {
        val h2 = H2(TestDatabase, usedTablesMock)

        val articles: List<Article> = transaction(h2.instance) {
            Articles.selectAll()
                .orderBy(Articles.createdAt)
                .map { it.toArticle() }
        }

        assertEquals(articleTestData, articles)
    }
}
