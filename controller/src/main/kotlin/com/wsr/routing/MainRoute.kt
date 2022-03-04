package com.wsr.routing

import com.wsr.routing.articles.comments.create.commentsCreateRoute
import com.wsr.routing.articles.comments.get.commentsGetRoute
import com.wsr.routing.articles.comments.repies.create.repliesCreateRoute
import com.wsr.routing.articles.create.articlesCreateRoute
import com.wsr.routing.articles.delete.articlesDeleteRoute
import com.wsr.routing.articles.get.articleGetRoute
import com.wsr.routing.articles.update.articlesUpdateRoute
import io.ktor.application.*
import io.ktor.routing.*

fun Application.mainRoute() {
    routing {
        route("/articles") {
            articleGetRoute()
            articlesCreateRoute()
            articlesUpdateRoute()
            articlesDeleteRoute()

            route("/{article_id}/comments") {
                commentsGetRoute()
                commentsCreateRoute()

                route("/{comment_id}/replies") {
                    repliesCreateRoute()
                }
            }
        }
    }
}
