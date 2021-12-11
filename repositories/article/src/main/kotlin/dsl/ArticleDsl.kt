package dsl

import external.ExternalArticle
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction

interface ArticleDsl {
    fun getAll(database: Database): List<ExternalArticle>
    fun getById(database: Database, id: String): ExternalArticle
    fun insert(database: Database, externalArticle: ExternalArticle): Transaction
}
