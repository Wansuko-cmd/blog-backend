package exceptions

sealed class DomainException(override val message: String) : Throwable(message) {

    class ValidationException(override val message: String = "") : DomainException(message)
}
