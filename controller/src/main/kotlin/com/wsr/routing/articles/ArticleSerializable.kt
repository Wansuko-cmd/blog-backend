package com.wsr.routing.articles

import article.ArticleUseCaseModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleSerializable(
    val id: String,
    @SerialName("thumbnail_path") val thumbnailPath: String?,
    val title: String,
    val body: String,
    @SerialName("good_count") val goodCount: Int,
    @SerialName("created_at") val createdAt: LocalDateTime,
    @SerialName("modified_at") val modifiedAt: LocalDateTime,
) {
    companion object {
        fun ArticleUseCaseModel.toSerializable() = ArticleSerializable(
            id = id,
            thumbnailPath = thumbnailPath,
            title = title,
            body = body,
            goodCount = goodCount,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
        )
    }
}
