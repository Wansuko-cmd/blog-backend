package mock

import entities.article.Article
import kotlinx.datetime.LocalDateTime
import value_object.article.ArticleBody
import value_object.article.ArticleTitle
import value_object.article.GoodCount
import value_object.common.UniqueId

val time = LocalDateTime.parse("2005-05-05T15:00:00")

val articleTestData = listOf(
    Article(
        UniqueId("UniqueId1"),
        ArticleTitle("Title1"),
        ArticleBody("Body1"),
        GoodCount(1),
        time,
        time,
    ),
    Article(
        UniqueId("UniqueId2"),
        ArticleTitle("Title2"),
        ArticleBody("Body2"),
        GoodCount(2),
        time,
        time,
    ),
    Article(
        UniqueId("UniqueId3"),
        ArticleTitle("Title3"),
        ArticleBody("Body3"),
        GoodCount(3),
        time,
        time,
    ),
    Article(
        UniqueId("UniqueId4"),
        ArticleTitle("Title4"),
        ArticleBody("Body4"),
        GoodCount(4),
        time,
        time,
    )
)
