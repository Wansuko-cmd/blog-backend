package com.wsr.integration

import com.wsr.main
import io.ktor.server.testing.*

val testEngine: TestApplicationEngine by lazy {
    TestApplicationEngine().apply {
        start(wait = false)
        application.testDiModule()
        application.main()
    }
}
