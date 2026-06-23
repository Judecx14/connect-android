package com.fenix.domain.model.resource

sealed interface FailureReason {
    sealed class Hardware : FailureReason {
       data object NoInternet : Hardware()
       data object GpsDisabled: Hardware()
    }

    sealed class Api : FailureReason {
       data object BadRequest : Api()
       data object InternalServerError : Api()
    }

    data object Unknow : FailureReason
}