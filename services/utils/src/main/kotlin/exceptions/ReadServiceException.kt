package main.kotlin.exceptions

sealed class ReadServiceException(override val message: String) : Exception() {

    class NotFoundException(override val message: String) : ReadServiceException(message)
}
