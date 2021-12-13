package com.wsr.utils.routing

import com.wsr.utils.exceptions.ControllerException
import exceptions.ServiceException
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.coroutineScope
import logger.errorLog

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
    } catch (e: ServiceException) {

        errorLog(e, "ControllerにてServiceからのエラーを検出")

        val status = when(e) {
            is ServiceException.RecordNotFoundException ->
                HttpStatusCode.UnprocessableEntity to "record not found"
            is ServiceException.IllegalArgumentException ->
                HttpStatusCode.BadRequest to "Illegal argument error"
            is ServiceException.ServerErrorException ->
                HttpStatusCode.InternalServerError to "sorry"
        }
        val (statusCode, message) = status
        call.respond(statusCode, message)

    } catch (e: ControllerException) {

        errorLog(e, "ControllerにてControllerで生じたエラーを検出")

        val status = when(e) {
            is ControllerException.ParameterNotFoundException ->
                HttpStatusCode.UnprocessableEntity to "please set parameter"
            is ControllerException.IllegalParameterException ->
                HttpStatusCode.UnprocessableEntity to "illegal parameter"
        }
        val (statusCode, message) = status
        call.respond(statusCode, message)

    } catch (e: Exception) {
        errorLog(e, "Controllerにてハンドリングしていないエラーを検出")
        call.respond(HttpStatusCode.InternalServerError)
    }
}
