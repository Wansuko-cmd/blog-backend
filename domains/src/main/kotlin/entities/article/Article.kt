package entities.article

import kotlinx.datetime.LocalDateTime
import value_object.article.ArticleBody
import value_object.article.ArticleTitle
import value_object.article.GoodCount
import value_object.common.PrimaryKey
import java.util.*

data class Article(
    val id: PrimaryKey = PrimaryKey(UUID.randomUUID().toString()),
    val title: ArticleTitle,
    val body: ArticleBody,
    val goodCount: GoodCount,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
)
