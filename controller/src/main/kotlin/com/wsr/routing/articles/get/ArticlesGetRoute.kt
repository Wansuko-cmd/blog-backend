package com.wsr.routing.articles.get

import article.get.GetArticleUseCase
import com.wsr.routing.articles.ArticleSerializable.Companion.toSerializable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import state.consume
import state.onSuccess

fun Route.articleGetRoute() {

    val getArticleUseCase by inject<GetArticleUseCase>()

    get {
        getArticleUseCase.getAll()
            .onSuccess { list -> list.map { it.toSerializable() } }
            .consume(
                success = { call.respond(HttpStatusCode.OK, it) },
                failure = { call.respond(HttpStatusCode.InternalServerError, it) },
                empty = { call.respond(HttpStatusCode.NotFound) },
            )
    }

    get("{id}") {
        val id = call.parameters["id"] ?: return@get
        getArticleUseCase.getById(id)
            .onSuccess { it.toSerializable() }
            .consume(
                success = { call.respond(HttpStatusCode.OK, it) },
                failure = { call.respond(HttpStatusCode.InternalServerError, it) },
                empty = { call.respond(HttpStatusCode.NotFound) }
            )
    }
}
