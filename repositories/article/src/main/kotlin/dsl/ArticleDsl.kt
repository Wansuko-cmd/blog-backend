package dsl

import external.ExternalArticle
import org.jetbrains.exposed.sql.Database

interface ArticleDsl {
    fun getAll(database: Database): List<ExternalArticle>
    fun getById(database: Database, id: String): ExternalArticle
}
