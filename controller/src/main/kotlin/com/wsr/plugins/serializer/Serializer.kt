package com.wsr.plugins.serializer

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun Application.configureSerializer() {
    install(ContentNegotiation) {
        json(Json)
    }
}
