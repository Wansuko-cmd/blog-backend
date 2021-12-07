package com.wsr.routing.articles.get

import com.wsr.utils.routing.getRouteHandler
import core.ArticleService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.async
import value_object.common.UniqueId

fun Route.articleGetRoute(articleService: ArticleService) {
    getRouteHandler {
        val articles = async {
            articleService
                .getAll()
                .map { ArticleGetResponse.fromArticle(it) }
        }
        proceed()
        call.respond(articles.await())
    }

    getRouteHandler("{id}") {
        val id = call.parameters["id"] ?: return@getRouteHandler  call.respond(HttpStatusCode.BadRequest)
        val article = async { ArticleGetResponse.fromArticle(articleService.getById(UniqueId(id))) }
        proceed()
        call.respond(article.await())
    }
}
