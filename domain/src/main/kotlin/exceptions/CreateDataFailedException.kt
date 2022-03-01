package exceptions

sealed class CreateDataFailedException : Exception() {
    class DatabaseException : CreateDataFailedException()
}
