package com.wsr.utils.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.coroutineScope

fun Route.getRouteHandler(
    path: String = "",
    body: PipelineInterceptor<Unit, ApplicationCall>
): Route = route(path, HttpMethod.Get) { handle(baseRouteBody(body)) }

fun Route.postRouteHandler(
    path: String = "",
    body: PipelineInterceptor<Unit, ApplicationCall>
): Route = route(path, HttpMethod.Post) { handle(baseRouteBody(body)) }

fun Route.putRouteHandler(
    path: String = "",
    body: PipelineInterceptor<Unit, ApplicationCall>
): Route = route(path, HttpMethod.Put) { handle(baseRouteBody(body)) }

fun Route.deleteRouteHandler(
    path: String = "",
    body: PipelineInterceptor<Unit, ApplicationCall>
): Route = route(path, HttpMethod.Delete) { handle(baseRouteBody(body)) }

private fun baseRouteBody(
    body: PipelineInterceptor<Unit, ApplicationCall>
): PipelineInterceptor<Unit, ApplicationCall> = {
    try {
        coroutineScope { body(Unit) }
    } catch (e: Exception) {
        println("Catch Error")
        throw Exception()
    }
}
