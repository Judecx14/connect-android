package com.fenix.data.repository.user

import com.fenix.data.datasource.api.user.UserApi
import com.fenix.data.model.user.request.toRequest
import com.fenix.data.model.user.response.toUser
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.model.user.CreateUserProperties
import com.fenix.domain.model.user.User
import com.fenix.domain.repository.user.UserRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

internal fun failureReasonMapper(throwable: Throwable): FailureReason {
    return when (throwable) {
        is IOException -> FailureReason.Hardware.NO_INTERNET
        is HttpException -> when (throwable.code()) {
            404 -> FailureReason.Api.BAD_REQUEST
            else -> FailureReason.Api.INTERNAL_SERVER_ERROR
        }

        else -> FailureReason.Unknow.ANONYMOUS
    }
}

suspend fun <T, R> safeApiCall(
    call: suspend () -> R,
    onSuccess: (R) -> T,
    onFailure: (Throwable) -> FailureReason = { throwable -> failureReasonMapper(throwable) }
): Resource<T, FailureReason> {
    val result = runCatching { call() }

    return result.fold(
        onSuccess = { response -> Resource.Success(onSuccess(response)) },
        onFailure = { throwable -> Resource.Failure(onFailure(throwable)) }
    )
}

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun create(properties: CreateUserProperties): Resource<User, FailureReason> {
        val payloadRequest = properties.toRequest()

        return safeApiCall(
            call = { userApi.create(payloadRequest) },
            onSuccess = { response -> response.toUser() }
        )
    }
}