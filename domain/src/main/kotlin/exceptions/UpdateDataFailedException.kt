package exceptions

sealed class UpdateDataFailedException : Exception() {
    class DatabaseException : UpdateDataFailedException()
}
