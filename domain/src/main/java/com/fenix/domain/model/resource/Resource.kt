package com.fenix.domain.model.resource

sealed class Resource<out T, out E> {
    data class Success<T>(val data: T) : Resource<T, Nothing>()
    data class Failure<E>(val reason: E, val message: String? = null) : Resource<Nothing, E>()
}