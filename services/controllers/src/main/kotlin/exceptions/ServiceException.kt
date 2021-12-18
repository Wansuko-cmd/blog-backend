package exceptions

sealed class ServiceException(override val message: String) : Exception() {

    class RecordNotFoundException(override val message: String = "") : ServiceException(message)

    class ServerErrorException(override val message: String = "") : ServiceException(message)

    class IllegalArgumentException(override val message: String = "") : ServiceException(message)
}