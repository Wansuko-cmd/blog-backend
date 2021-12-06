package com.wsr.routing.articles

import com.wsr.routing.articles.get.articleGetRoute
import io.ktor.routing.*

fun Route.articlesRoute() {
    route("/articles") {
        articleGetRoute()
    }
}
