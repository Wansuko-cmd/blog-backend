package mock

import entities.article.Article
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tables.Articles
import value_objects.article.ArticleBody
import value_objects.article.ArticleTitle
import value_objects.article.GoodCount
import value_objects.common.UniqueId

val usedTablesMock = listOf<Table>(Articles)

fun ResultRow.toArticle() = Article(
    ArticleTitle(this[Articles.title]),
    ArticleBody(this[Articles.body]),
    GoodCount(this[Articles.goodCount]),
    this[Articles.createdAt].toKotlinLocalDateTime(),
    this[Articles.modifiedAt].toKotlinLocalDateTime(),
    UniqueId(this[Articles.id]),
)
