package com.fenix.data.datasource.api

import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import okio.IOException
import retrofit2.HttpException

internal fun failureReasonMapper(throwable: Throwable): FailureReason {
    return when (throwable) {
        is IOException -> FailureReason.Hardware.NoInternet

        is HttpException -> when (throwable.code()) {
            FailureReason.Api.BadRequest.code -> FailureReason.Api.BadRequest
            else -> FailureReason.Api.InternalServerError
        }

        else -> FailureReason.Unknown
    }
}

internal inline fun <R, T> safeCall(
    call: () -> R,
    onSuccess: (R) -> T,
    onFailure: (Throwable) -> FailureReason = { throwable -> failureReasonMapper(throwable) }
): Resource<T, FailureReason> {
    val result = runCatching { call() }

    return result.fold(
        onSuccess = { response -> Resource.Success(onSuccess(response)) },
        onFailure = { throwable -> Resource.Failure(onFailure(throwable)) }
    )
}

internal inline fun safeCall(
    call: () -> Unit,
    onFailure: (Throwable) -> FailureReason = { throwable -> failureReasonMapper(throwable) }
): Resource<Unit, FailureReason> {
    val result = runCatching { call() }

    return result.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { throwable -> Resource.Failure(onFailure(throwable)) }
    )
}


