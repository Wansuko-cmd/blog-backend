package com.wsr

import com.wsr.plugins.installPlugins
import com.wsr.routing.mainRoute
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.main() {
    installPlugins()

    mainRoute()
}
