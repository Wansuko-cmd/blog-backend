package dsl

import external.ExternalArticle
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Articles
import transaction.writeTransaction

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

    override fun insert(database: Database, externalArticle: ExternalArticle) =
        writeTransaction(database) {
            Articles.insert {
                it[id] = externalArticle.id
                it[title] = externalArticle.title
                it[body] = externalArticle.body
                it[goodCount] = externalArticle.goodCount
                it[createdAt] = externalArticle.createdAt.toJavaLocalDateTime()
                it[modifiedAt] = externalArticle.modifiedAt.toJavaLocalDateTime()
            }
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
