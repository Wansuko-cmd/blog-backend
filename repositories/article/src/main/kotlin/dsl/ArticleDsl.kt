package dsl

import entities.article.Article
import org.jetbrains.exposed.sql.Database
import value_object.common.UniqueId

interface ArticleDsl {
    fun getAll(database: Database): List<Article>
    fun getById(database: Database, id: UniqueId): Article
}
