package dsl

import article.ExternalArticle
import exceptions.RepositoryException
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Articles

class ArticleDslImpl : ArticleDsl {

    override fun getAll(database: Database): List<ExternalArticle> = transaction(database) {
        Articles.selectAll()
            .map { it.toExternalArticle() }
    }

    override fun getById(database: Database, id: String): ExternalArticle = transaction(database) {
        Articles.select { Articles.id eq id }
            .first()
            .toExternalArticle()
    }

    override fun insert(database: Database, externalArticle: ExternalArticle) {
        transaction(database) {
            Articles.insert {
                it[id] = externalArticle.id
                it[title] = externalArticle.title
                it[body] = externalArticle.body
                it[goodCount] = externalArticle.goodCount
                it[createdAt] = externalArticle.createdAt.toJavaLocalDateTime()
                it[modifiedAt] = externalArticle.modifiedAt.toJavaLocalDateTime()
            }
        }
    }

    override fun update(database: Database, externalArticle: ExternalArticle) {
        val updated = transaction(database) {
            Articles.update({ Articles.id eq externalArticle.id }, limit = 1) {
                it[title] = externalArticle.title
                it[body] = externalArticle.body
                it[goodCount] = externalArticle.goodCount
                it[modifiedAt] = externalArticle.modifiedAt.toJavaLocalDateTime()
            }
        }
        when {
            updated < 1 -> throw NoSuchElementException()
            1 < updated -> throw RepositoryException.DatabaseErrorException("Articleにて2つ以上のカラムを更新")
        }
    }

    override fun delete(database: Database, id: String) {
        val deleted = transaction(database) {
            Articles.deleteWhere(limit = 1) { Articles.id eq id }
        }
        when {
            deleted < 1 -> throw NoSuchElementException()
            1 < deleted -> throw RepositoryException.DatabaseErrorException("Articleにて2つ以上のカラムを削除")
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
