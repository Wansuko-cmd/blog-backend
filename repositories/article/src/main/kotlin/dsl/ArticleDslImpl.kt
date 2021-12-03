package dsl

import entities.article.Article
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import table.Articles
import value_object.article.ArticleBody
import value_object.article.ArticleTitle
import value_object.article.GoodCount
import value_object.common.UniqueId

class ArticleDslImpl(private val database: Database) : ArticleDsl {

    override fun getAll(): List<Article> = transaction(database) {
        Articles.selectAll()
            .orderBy(Articles.createdAt)
            .map { it.toArticle() }
    }

    override fun getById(id: UniqueId): Article = transaction(database) {
        Articles.select { Articles.id eq id.value }
            .first()
            .toArticle()
    }

    private fun ResultRow.toArticle() = Article(
        UniqueId(this[Articles.id]),
        ArticleTitle(this[Articles.title]),
        ArticleBody(this[Articles.body]),
        GoodCount(this[Articles.goodCount]),
        this[Articles.createdAt].toKotlinLocalDateTime(),
        this[Articles.modifiedAt].toKotlinLocalDateTime()
    )
}
