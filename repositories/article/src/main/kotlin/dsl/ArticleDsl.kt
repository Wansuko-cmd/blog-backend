package dsl

import entities.article.Article
import value_object.common.UniqueId

interface ArticleDsl {
    fun getAll(): List<Article>
    fun getById(id: UniqueId): Article
}
