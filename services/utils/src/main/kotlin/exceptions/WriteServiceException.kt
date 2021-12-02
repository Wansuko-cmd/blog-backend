package exceptions

sealed class WriteServiceException(override val message: String) : Exception() {

}
