package com.wsr.routing.articles.comments

import kotlinx.serialization.Serializable

@Serializable
data class CommentsCreateRequest(
    val body: String,
)
