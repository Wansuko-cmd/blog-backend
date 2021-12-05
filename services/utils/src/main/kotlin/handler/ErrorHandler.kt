package handler

import mu.KotlinLogging

inline fun <TClass: Any, Result> TClass.readErrorHandler(block: () -> Result): Result = try {
    block()
} catch (e: Exception) {
    val logger = KotlinLogging.logger {}
    logger.error(e) { "Error Occurred in ${javaClass.simpleName}" }
    throw e
}

inline fun <T> writeErrorHandler(block: () -> T): T = try {
    block()
} catch (e: Exception) {
    throw e
}
