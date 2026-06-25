package com.fenix.data.repository.user

import com.fenix.data.datasource.api.safeCall
import com.fenix.data.datasource.api.user.UserApi
import com.fenix.data.model.user.request.toRequest
import com.fenix.data.model.user.response.toUser
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.model.user.CreateUserInput
import com.fenix.domain.model.user.User
import com.fenix.domain.repository.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun create(input: CreateUserInput): Resource<User, FailureReason> {
        val payloadRequest = input.toRequest()

        return safeCall(
            call = { userApi.create(payloadRequest) },
            onSuccess = { response -> response.toUser() }
        )
    }
}