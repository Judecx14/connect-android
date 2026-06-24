package com.fenix.domain.model.resource

sealed interface FailureReason {
    sealed class Hardware : FailureReason {
       data object NoInternet : Hardware()
       data object GpsDisabled: Hardware()
    }

    sealed class Api(val code: Int) : FailureReason {
       data object BadRequest : Api(400)
       data object InternalServerError : Api(505)
    }

    data object Unknown : FailureReason
}