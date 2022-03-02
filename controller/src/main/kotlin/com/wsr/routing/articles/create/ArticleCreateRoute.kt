package com.wsr.routing.articles.create

import article.create.CreateArticleUseCase
import com.wsr.routing.articles.ArticleSerializable.Companion.toSerializable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import state.consume
import state.map

fun Route.articleCreateRoute() {

    val createArticleUseCase by inject<CreateArticleUseCase>()

    post {
        val (thumbnail, title, body) = call.receive<ArticleCreateRequest>()
        createArticleUseCase.create(thumbnail, title, body)
            .map { it.toSerializable() }
            .consume(
                success = { call.respond(HttpStatusCode.OK, it) },
                failure = { call.respond(HttpStatusCode.InternalServerError, it) },
                empty = { call.respond(HttpStatusCode.NotFound) },
            )
    }
}
