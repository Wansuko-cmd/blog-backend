package exceptions

sealed class GetDataFailedException : Exception() {
    class DatabaseException : GetDataFailedException()
}
