package com.wsr.routing.articles.create

import kotlinx.serialization.Serializable

@Serializable
data class ArticleCreateRequest(
    val title: String,
    val body: String,
    val goodCount: Int,
)
