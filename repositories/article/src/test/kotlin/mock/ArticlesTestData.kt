package mock

import article.ExternalArticle
import kotlinx.datetime.LocalDateTime

val time = LocalDateTime.parse("2005-05-05T15:00:00")

val TestData = listOf(
    ExternalArticle(
        "UniqueId1",
        "Title1",
        "Body1",
        1,
        time,
        time,
    ),
    ExternalArticle(
        "UniqueId2",
        "Title2",
        "Body2",
        2,
        time,
        time,
    ),
    ExternalArticle(
        "UniqueId3",
        "Title3",
        "Body3",
        3,
        time,
        time,
    ),
    ExternalArticle(
        "UniqueId4",
        "Title4",
        "Body4",
        4,
        time,
        time,
    ),
)
