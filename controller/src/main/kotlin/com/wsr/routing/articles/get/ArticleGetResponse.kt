package com.wsr.routing.articles.get

import article.ArticleUseCaseModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleGetResponse(
    val id: String,
    val title: String,
    val body: String,
    @SerialName("good_count") val goodCount: Int,
    @SerialName("created_at") val createdAt: LocalDateTime,
    @SerialName("modified_at") val modifiedAt: LocalDateTime,
) {
    companion object {
        fun ArticleUseCaseModel.toSerializable() = ArticleGetResponse(
            id, title, body, goodCount, createdAt, modifiedAt
        )
    }
}
