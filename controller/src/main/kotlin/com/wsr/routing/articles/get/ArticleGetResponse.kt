package com.wsr.routing.articles.get

import entities.article.Article
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
        fun fromArticle(article: Article): ArticleGetResponse {
            return ArticleGetResponse(
                article.id.value,
                article.title.value,
                article.body.value,
                article.goodCount.value,
                article.createdAt,
                article.modifiedAt
            )
        }
    }
}
