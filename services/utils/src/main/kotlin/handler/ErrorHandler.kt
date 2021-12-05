package handler

import logger.errorLog

inline fun <TClass: Any, Result> TClass.readErrorHandler(block: () -> Result): Result = try {
    block()
} catch (e: Exception) {
    errorLog(e, "Error occurred", mapOf("class" to javaClass.simpleName))
    throw e
}

inline fun <T> writeErrorHandler(block: () -> T): T = try {
    block()
} catch (e: Exception) {
    throw e
}
