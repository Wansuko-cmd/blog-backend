package com.wsr.routing.articles.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesUpdateRequest(
    @SerialName("thumbnail_path") val thumbnailPath: String?,
    val title: String,
    val body: String,
)
