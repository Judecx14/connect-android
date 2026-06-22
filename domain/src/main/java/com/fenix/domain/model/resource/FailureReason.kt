package com.fenix.domain.model.resource

sealed interface FailureReason {
    enum class Hardware : FailureReason {
        NO_INTERNET,
    }

    enum class Api : FailureReason {
        BAD_REQUEST,
        INTERNAL_SERVER_ERROR
    }

    enum class Unknow : FailureReason {
        ANONYMOUS
    }
}