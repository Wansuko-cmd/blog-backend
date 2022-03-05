package com.wsr.routing.articles.create

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesCreateRequest(
    @SerialName("thumbnail_path") val thumbnailPath: String?,
    val title: String,
    val body: String,
)
