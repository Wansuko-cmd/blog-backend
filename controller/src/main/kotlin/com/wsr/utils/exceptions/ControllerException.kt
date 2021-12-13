package com.wsr.utils.exceptions

sealed class ControllerException(override val message: String) : Exception() {

    class ParameterNotFoundException(override val message: String = "") : ControllerException(message)

    class IllegalParameterException(override val message: String = "") : ControllerException(message)
}
