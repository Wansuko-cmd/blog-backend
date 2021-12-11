package exceptions

sealed class ServiceException(override val message: String) : Throwable(message) {

    class NotFoundException(override val message: String = "") : ServiceException(message)

    class DatabaseErrorException(override val message: String = "") : ServiceException(message)

    class IllegalArgumentException(override val message: String = "") : ServiceException(message)
}
