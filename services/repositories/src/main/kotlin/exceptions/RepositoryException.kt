package exceptions

sealed class RepositoryException(override val message: String) : Exception() {

    class NotFoundException(override val message: String = "") : RepositoryException(message)

    class DatabaseErrorException(override val message: String = "") : RepositoryException(message)
}
