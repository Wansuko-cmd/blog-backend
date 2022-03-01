package exceptions

sealed class InsertDataFailedException : Exception() {
    class DatabaseException : InsertDataFailedException()
}
