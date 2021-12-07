package com.wsr.routing.articles.get

import external.ExternalArticle
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
){
    companion object {
        fun fromExternalArticle(externalArticle: ExternalArticle): ArticleGetResponse {
            return ArticleGetResponse(
                externalArticle.id,
                externalArticle.title,
                externalArticle.body,
                externalArticle.goodCount,
                externalArticle.createdAt,
                externalArticle.modifiedAt
            )
        }
    }
}
