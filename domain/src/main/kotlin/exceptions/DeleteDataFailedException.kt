package exceptions

sealed class DeleteDataFailedException : Exception() {
    class DatabaseException : DeleteDataFailedException()
}
