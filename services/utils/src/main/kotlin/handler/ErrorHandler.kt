package handler

import enum.IsSuccess
import exceptions.DomainException
import exceptions.ServiceException
import logger.errorLog

inline fun <TClass: Any, Result> TClass.readErrorHandler(block: () -> Result): Result = try {
    block()
} catch (e: Exception) {
    errorLog(e, "Error occurred", mapOf("class" to javaClass.simpleName))
    throw e
}

inline fun <TClass: Any> TClass.writeErrorHandler(block: () -> IsSuccess): IsSuccess = try {
    block()
} catch (e: DomainException.ValidationException) {
    throw ServiceException.IllegalArgumentException()
} catch (e: Exception) {
    errorLog(e, "Error occurred", mapOf("class" to javaClass.simpleName))
    throw e
}
