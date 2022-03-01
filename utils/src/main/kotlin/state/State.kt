package state

sealed class State<out T, out E> {
    class Success<T>(val value: T) : State<T, Nothing>()
    class Failure<E>(val value: E) : State<Nothing, E>()
    object Empty : State<Nothing, Nothing>()
}

inline fun <T, E, NT> State<T, E>.map(block: (T) -> NT): State<NT, E> = when (this) {
    is State.Success -> State.Success(block(value))
    is State.Failure -> this
    is State.Empty -> this
}

inline fun <T, E, NT> State<T, E>.flatMap(block: (T) -> State<NT, E>): State<NT, E> = when (this) {
    is State.Success -> block(value)
    is State.Failure -> this
    is State.Empty -> this
}

inline fun <T, E> State<T, E>.consume(
    success: (T) -> Unit,
    failure: (E) -> Unit,
    empty: () -> Unit,
): Unit = when (this) {
    is State.Success -> success(value)
    is State.Failure -> failure(value)
    is State.Empty -> empty()
}
