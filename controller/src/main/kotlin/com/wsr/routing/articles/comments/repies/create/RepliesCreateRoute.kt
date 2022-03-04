package com.wsr.routing.articles.comments.repies.create

import com.wsr.routing.articles.comments.CommentsCreateRequest
import com.wsr.routing.articles.comments.ReplySerializable.Companion.toSerializable
import comment.create.CreateReplyUseCase
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import state.consume
import state.map

fun Route.repliesCreateRoute() {

    val createReplyUseCase by inject<CreateReplyUseCase>()

    post {
        val commentId = call.parameters["comment_id"] ?: return@post run { call.respond(HttpStatusCode.BadRequest) }
        val (body) = call.receive<CommentsCreateRequest>()
        createReplyUseCase.create(commentId, body)
            .map { it.toSerializable() }
            .consume(
                success = { call.respond(HttpStatusCode.OK, it) },
                failure = { call.respond(HttpStatusCode.InternalServerError, it) },
                empty = { call.respond(HttpStatusCode.NotFound) }
            )
    }
}
