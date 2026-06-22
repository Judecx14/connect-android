package com.fenix.data.datasource.api.user

import com.fenix.data.model.user.request.CreateUserRequest
import com.fenix.data.model.user.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

const val userEndpoint = "user"

interface UserApi {
    @POST(userEndpoint)
    suspend fun create(@Body payload: CreateUserRequest) : UserResponse
}