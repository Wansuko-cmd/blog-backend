package handler

interface ErrorHandler

inline fun <T> ErrorHandler.readErrorHandler(block: () -> T): T = try {
    block()
} catch (e: Exception) {
    throw e
}

inline fun <T> ErrorHandler.writeErrorHandler(block: () -> T): T = try {
    block()
} catch (e: Exception) {
    throw e
}
