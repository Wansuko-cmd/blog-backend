package com.wsr.routing.articles.update

import kotlinx.serialization.Serializable

@Serializable
data class ArticleUpdateRequest(
    val id: String,
    val title: String,
    val body: String,
    val goodCount: Int,
)
