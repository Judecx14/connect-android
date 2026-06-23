package com.fenix.data.repository.user

import com.fenix.data.datasource.api.safeApiCall
import com.fenix.data.datasource.api.user.UserApi
import com.fenix.data.model.user.request.toRequest
import com.fenix.data.model.user.response.toUser
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.model.user.CreateUserProperties
import com.fenix.domain.model.user.User
import com.fenix.domain.repository.user.UserRepository
import javax.inject.Inject

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