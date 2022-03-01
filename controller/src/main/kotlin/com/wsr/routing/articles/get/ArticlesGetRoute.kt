package com.wsr.routing.articles.get

import article.get.GetArticleUseCase
import com.wsr.routing.articles.get.ArticleGetResponse.Companion.toSerializable
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import state.consume
import state.map

fun Route.articleGetRoute() {

    val getArticleUseCase by inject<GetArticleUseCase>()

    get {
        getArticleUseCase.getAll()
            .map { list -> list.map { it.toSerializable() } }
            .consume(
                success = { call.respond(it) },
                failure = { call.respond(it) },
                empty = { },
            )
    }

    get("{id}") {
        val id = call.parameters["id"] ?: return@get
        getArticleUseCase.getById(id)
            .map { it.toSerializable() }
            .consume(
                success = { call.respond(it) },
                failure = { call.respond(it) },
                empty = { }
            )
    }
}
