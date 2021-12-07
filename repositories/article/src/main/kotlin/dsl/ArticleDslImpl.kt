package dsl

import external.ExternalArticle
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Articles

class ArticleDslImpl : ArticleDsl {

    override fun getAll(database: Database): List<ExternalArticle> = transaction(database) {
        Articles.selectAll()
            .orderBy(Articles.createdAt)
            .map { it.toExternalArticle() }
    }

    override fun getById(database: Database, id: String): ExternalArticle = transaction(database) {
        Articles.select { Articles.id eq id }
            .first()
            .toExternalArticle()
    }

    private fun ResultRow.toExternalArticle() = ExternalArticle(
        this[Articles.id],
        this[Articles.title],
        this[Articles.body],
        this[Articles.goodCount],
        this[Articles.createdAt].toKotlinLocalDateTime(),
        this[Articles.modifiedAt].toKotlinLocalDateTime()
    )
}
