package com.wsr.routing.articles.update

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesUpdateRequest(
    val id: String,
    val thumbnailPath: String?,
    val title: String,
    val body: String,
    val goodCount: Int,
)
