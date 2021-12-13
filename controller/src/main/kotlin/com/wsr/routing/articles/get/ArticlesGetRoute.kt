package com.wsr.routing.articles.get

import com.wsr.utils.exceptions.ControllerException
import com.wsr.utils.routing.getRouteHandler
import core.ArticleService
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.async

fun Route.articleGetRoute(articleService: ArticleService) {

    getRouteHandler {
        val page = call.request.queryParameters["page"]
        val offset = call.request.queryParameters["offset"]

        val articles = async {

            val externalArticle = if(page != null && offset != null) {
                articleService.getWithPaginate(
                    page.toIntOrNull() ?: throw ControllerException.IllegalParameterException(),
                    offset.toIntOrNull() ?: throw ControllerException.IllegalParameterException()
                )
            } else { articleService.getAll() }
            externalArticle.map { ArticleGetResponse.fromExternalArticle(it) }
        }
        proceed()
        call.respond(articles.await())
    }

    getRouteHandler("{id}") {
        val id = call.parameters["id"] ?: throw ControllerException.ParameterNotFoundException()
        val article = async {
            ArticleGetResponse
                .fromExternalArticle(articleService.getById(id))
        }
        proceed()
        call.respond(article.await())
    }
}
