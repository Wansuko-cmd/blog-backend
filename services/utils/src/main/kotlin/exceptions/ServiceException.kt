package exceptions

sealed class ServiceException(override val message: String) : Exception() {

    class NotFoundException(override val message: String = "") : ServiceException(message)

    class DatabaseErrorException(override val message: String = "") : ServiceException(message)
}
