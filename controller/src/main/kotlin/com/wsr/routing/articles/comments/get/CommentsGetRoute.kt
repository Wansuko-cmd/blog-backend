package com.wsr.routing.articles.comments.get

import com.wsr.routing.articles.comments.CommentSerializable.Companion.toSerializable
import comment.get.GetCommentUseCase
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import state.consume
import state.map

fun Route.commentsGetRoute() {

    val getCommentUseCase by inject<GetCommentUseCase>()

    get {
        val articleId = call.parameters["article_id"] ?: return@get run { call.respond(HttpStatusCode.BadRequest) }
        getCommentUseCase.getAllByArticleId(articleId)
            .map { comments -> comments.map { it.toSerializable() } }
            .consume(
                success = { call.respond(HttpStatusCode.OK, it) },
                failure = { call.respond(HttpStatusCode.InternalServerError, it) },
                empty = { call.respond(HttpStatusCode.NotFound) },
            )
    }
}
