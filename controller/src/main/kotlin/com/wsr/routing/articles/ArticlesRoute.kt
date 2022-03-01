package com.wsr.routing.articles

import com.wsr.routing.articles.create.articleCreateRoute
import com.wsr.routing.articles.delete.articleDeleteRoute
import com.wsr.routing.articles.get.articleGetRoute
import com.wsr.routing.articles.update.articleUpdateRoute
import io.ktor.routing.*

fun Route.articlesRoute() {

    route("/articles") {
        articleGetRoute()
        articleCreateRoute()
        articleUpdateRoute()
        articleDeleteRoute()
    }
}
