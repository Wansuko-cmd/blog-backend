package handler

import enum.IsSuccess
import exceptions.DomainException
import exceptions.RepositoryException
import exceptions.ServiceException
import logger.errorLog

inline fun <TClass: Any, Result> TClass.readErrorHandler(block: () -> Result): Result = try {
    block()
} catch (e: RepositoryException) {
    errorLog(e, "リポジトリにてエラー発生", mapOf("class" to this.javaClass.simpleName))

    throw when(e) {
        is RepositoryException.NotFoundException -> ServiceException.NotFoundException()
        is RepositoryException.DatabaseErrorException -> ServiceException.ServerErrorException()
    }

} catch (e: Exception) {
    errorLog(e, "エラー発生", mapOf("class" to this.javaClass.simpleName))
    throw ServiceException.ServerErrorException()
}



inline fun <TClass: Any> TClass.writeErrorHandler(block: () -> IsSuccess): IsSuccess = try {
    block()
} catch (e: DomainException) {
    errorLog(e, "ドメインにてエラー発生", mapOf("class" to this.javaClass.simpleName))

    throw when(e) {
        is DomainException.ValidationException -> ServiceException.IllegalArgumentException()
    }

} catch (e: RepositoryException) {
    errorLog(e, "リポジトリにてエラー発生", mapOf("class" to this.javaClass.simpleName))

    throw when(e) {
        is RepositoryException.NotFoundException -> ServiceException.NotFoundException()
        is RepositoryException.DatabaseErrorException -> ServiceException.ServerErrorException()
    }

} catch (e: Exception) {
    errorLog(e, "エラー発生", mapOf("class" to this.javaClass.simpleName))
    throw ServiceException.ServerErrorException()
}
