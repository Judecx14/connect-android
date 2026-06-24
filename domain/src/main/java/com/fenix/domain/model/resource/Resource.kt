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
        is Resource.Failure<E> -> onFailure(this)
    }
}