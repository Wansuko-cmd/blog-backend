package entities.article

import api.now
import kotlinx.datetime.LocalDateTime
import value_object.article.ArticleBody
import value_object.article.ArticleTitle
import value_object.article.GoodCount
import value_object.common.UniqueId
import java.util.*

data class Article(
    val title: ArticleTitle,
    val body: ArticleBody,
    val goodCount: GoodCount = GoodCount(0),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime = LocalDateTime.now(),
    val id: UniqueId = UniqueId(UUID.randomUUID().toString()),
)
