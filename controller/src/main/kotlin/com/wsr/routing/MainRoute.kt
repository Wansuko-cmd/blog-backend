package com.wsr.routing

import com.wsr.routing.articles.articlesRoute
import io.ktor.application.*
import io.ktor.routing.*

fun Application.mainRoute() {
    routing {
        articlesRoute()
    }
}
