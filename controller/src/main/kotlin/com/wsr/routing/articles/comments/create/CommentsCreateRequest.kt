package com.wsr.routing.articles.comments.create

import kotlinx.serialization.Serializable

@Serializable
data class CommentsCreateRequest(
    val body: String,
)
