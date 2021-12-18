package dsl

import article.ExternalArticle
import org.jetbrains.exposed.sql.Database

interface ArticleDsl {
    fun getAll(database: Database): List<ExternalArticle>
    fun getById(database: Database, id: String): ExternalArticle
    fun insert(database: Database, externalArticle: ExternalArticle)
    fun update(database: Database, externalArticle: ExternalArticle)
    fun delete(database: Database, id: String)
}
