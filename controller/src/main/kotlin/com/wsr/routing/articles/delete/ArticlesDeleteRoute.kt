package com.wsr.routing.articles.delete

import article.delete.DeleteArticleUseCase
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import state.consume

fun Route.articlesDeleteRoute() {

    val deleteArticleUseCase by inject<DeleteArticleUseCase>()

    delete("{id}") {
        val id = call.parameters["id"] ?: return@delete run { call.respond(HttpStatusCode.BadRequest) }
        deleteArticleUseCase.delete(id)
            .consume(
                success = { call.respond(HttpStatusCode.OK) },
                failure = { call.respond(HttpStatusCode.InternalServerError, it) },
                empty = { call.respond(HttpStatusCode.NotFound) },
            )
    }
}
