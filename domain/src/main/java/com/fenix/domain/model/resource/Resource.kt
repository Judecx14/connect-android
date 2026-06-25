package com.fenix.domain.model.resource

sealed class Resource<out T, out E> {
    data class Success<T>(val data: T) : Resource<T, Nothing>()
    data class Failure<E>(val reason: E, val message: String? = null) : Resource<Nothing, E>()
}

inline fun <T, E> Resource<T, E>.bind(
    onFailure: (Resource.Failure<E>) -> Nothing
): T {
    return when (this) {
        is Resource.Success -> this.data
        is Resource.Failure -> onFailure(this)
    }
}

inline fun <T, E, R> Resource<T, E>.fold(
    onSuccess: (T) -> R,
    onFailure: (E) -> R
): R {
    return when (this) {
        is Resource.Success -> onSuccess(this.data)
        is Resource.Failure -> onFailure(this.reason)
    }
}

inline fun <T, E> Resource<T, E>.onFail(block: (Resource.Failure<E>) -> Unit) {
    if (this is Resource.Failure) {
        block(this)
    }
}

inline fun <T, E> Resource<T, E>.onSuccess(block: (T) -> Unit) {
    if (this is Resource.Success) {
        block(this.data)
    }
}


fun <T, E> Resource<T, E>.getOrNull(): T? = when (this) {
    is Resource.Success -> this.data
    is Resource.Failure -> null
}