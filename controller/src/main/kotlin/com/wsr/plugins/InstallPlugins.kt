package com.wsr.plugins

import com.wsr.plugins.koin.configureKoin
import com.wsr.plugins.logger.configureLogger
import com.wsr.plugins.serializer.configureSerializer
import io.ktor.application.*

fun Application.installPlugins() {
    configureLogger()
    configureSerializer()
    configureKoin()
}
