package mock

import entities.article.Article
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tables.Articles
import value_object.article.ArticleBody
import value_object.article.ArticleTitle
import value_object.article.GoodCount
import value_object.common.UniqueId

val usedTablesMock = listOf<Table>(Articles)

fun ResultRow.toArticle() = Article(
    ArticleTitle(this[Articles.title]),
    ArticleBody(this[Articles.body]),
    GoodCount(this[Articles.goodCount]),
    this[Articles.createdAt].toKotlinLocalDateTime(),
    this[Articles.modifiedAt].toKotlinLocalDateTime(),
    UniqueId(this[Articles.id]),
)
