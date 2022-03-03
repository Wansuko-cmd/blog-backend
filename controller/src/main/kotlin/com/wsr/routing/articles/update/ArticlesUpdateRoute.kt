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
import state.map

fun Route.articlesUpdateRoute() {

    val updateArticleUseCase by inject<UpdateArticleUseCase>()

    put {
        val (id, thumbnail, title, body) = call.receive<ArticlesUpdateRequest>()
        updateArticleUseCase.update(id, thumbnail, title, body)
            .map { it.toSerializable() }
            .consume(
                success = { call.respond(HttpStatusCode.OK, it) },
                failure = { call.respond(HttpStatusCode.InternalServerError, it) },
                empty = { call.respond(HttpStatusCode.NotFound) },
            )
    }
}