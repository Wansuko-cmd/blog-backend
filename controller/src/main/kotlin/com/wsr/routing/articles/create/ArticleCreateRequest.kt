package com.wsr.routing.articles.create

import kotlinx.serialization.Serializable

@Serializable
data class ArticleCreateRequest(
    val thumbnailPath: String?,
    val title: String,
    val body: String,
    val goodCount: Int,
)
