package com.wsr.routing.articles.comments.create

import com.wsr.routing.articles.comments.CommentSerializable.Companion.toSerializable
import com.wsr.routing.articles.comments.CommentsCreateRequest
import comment.create.CreateCommentUseCase
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import state.consume
import state.map

fun Route.commentsCreateRoute() {

    val createCommentUseCase by inject<CreateCommentUseCase>()


    post {
        val articleId = call.parameters["article_id"] ?: return@post run { call.respond(HttpStatusCode.BadRequest) }
        val (body) = call.receive<CommentsCreateRequest>()
        createCommentUseCase.create(articleId, body)
            .map { it.toSerializable() }
            .consume(
                success = { call.respond(HttpStatusCode.OK, it) },
                failure = { call.respond(HttpStatusCode.InternalServerError, it) },
                empty = { call.respond(HttpStatusCode.NotFound) }
            )
    }
}
