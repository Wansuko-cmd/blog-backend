package com.wsr.routing.articles.get

import article.SearchArticleService
import com.wsr.utils.exceptions.ControllerException
import com.wsr.utils.routing.getRouteHandler
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.async

fun Route.articleGetRoute(searchArticleService: SearchArticleService) {

    getRouteHandler {
        val page = call.request.queryParameters["page"]
        val offset = call.request.queryParameters["offset"]

        val articles = async {

            val externalArticle = if(page != null && offset != null) {
                searchArticleService.getWithPaginate(
                    page.toIntOrNull() ?: throw ControllerException.IllegalParameterException(),
                    offset.toIntOrNull() ?: throw ControllerException.IllegalParameterException()
                )
            } else { searchArticleService.getAll() }
            externalArticle.map { ArticleGetResponse.fromExternalArticle(it) }
        }
        proceed()
        call.respond(articles.await())
    }

    getRouteHandler("{id}") {
        val id = call.parameters["id"] ?: throw ControllerException.ParameterNotFoundException()
        val article = async {
            ArticleGetResponse
                .fromExternalArticle(searchArticleService.getById(id))
        }
        proceed()
        call.respond(article.await())
    }
}
