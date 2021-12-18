package com.wsr.routing.articles

import article.SearchArticleService
import com.wsr.routing.articles.get.articleGetRoute
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.articlesRoute() {

    val searchArticleService by inject<SearchArticleService>()

    route("/articles") {
        articleGetRoute(searchArticleService)
    }
}
