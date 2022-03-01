package com.wsr.routing.articles.update

import article.update.UpdateArticleUseCase
import com.wsr.routing.articles.ArticleSerializable.Companion.toSerializable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import state.consume
import state.onSuccess

fun Route.articleUpdateRoute() {

    val updateArticleUseCase by inject<UpdateArticleUseCase>()

    put {
        val (id, title, body) = call.receive<ArticleUpdateRequest>()
        updateArticleUseCase.update(id, title, body)
            .onSuccess { it.toSerializable() }
            .consume(
                success = { call.respond(HttpStatusCode.OK, it) },
                failure = { call.respond(HttpStatusCode.InternalServerError, it) },
                empty = { call.respond(HttpStatusCode.NotFound) },
            )
    }
}
