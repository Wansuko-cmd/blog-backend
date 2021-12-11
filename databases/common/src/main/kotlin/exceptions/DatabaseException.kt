package exceptions

sealed class DatabaseException(override val message: String) : Exception() {

    class DatabaseErrorException(override val message: String = "") : DatabaseException(message)
}
