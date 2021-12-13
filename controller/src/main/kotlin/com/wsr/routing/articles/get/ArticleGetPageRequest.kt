package com.wsr.routing.articles.get

import kotlinx.serialization.Serializable

@Serializable
data class ArticleGetPageRequest(
    val page: Int,
    val offset: Int,
)
