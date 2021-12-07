package com.wsr.routing.articles

import com.wsr.routing.articles.get.articleGetRoute
import core.ArticleService
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.articlesRoute() {

    val articleService by inject<ArticleService>()

    route("/articles") {
        articleGetRoute(articleService)
    }
}
