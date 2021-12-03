package entities.article

import kotlinx.datetime.LocalDateTime
import value_object.article.ArticleBody
import value_object.article.ArticleTitle
import value_object.article.GoodCount
import value_object.common.UniqueId
import java.util.*

data class Article(
    val id: UniqueId = UniqueId(UUID.randomUUID().toString()),
    val title: ArticleTitle,
    val body: ArticleBody,
    val goodCount: GoodCount,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
)
