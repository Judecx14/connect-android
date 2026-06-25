package com.fenix.domain.repository.auth

import com.fenix.domain.model.auth.AuthState
import com.fenix.domain.model.auth.AuthCredentials
import com.fenix.domain.model.auth.SignUpInput
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource

interface AuthRepository {
    suspend fun signUp(input: SignUpInput): Resource<Unit, FailureReason>
    suspend fun login(credentials: AuthCredentials): Resource<Unit, FailureReason>
    suspend fun getJwt(): Resource<String, FailureReason>
    fun getAuthProviderId(): Resource<String, FailureReason>
    fun getAuthState(): Resource<AuthState, FailureReason>
    fun logout():  Resource<Unit, FailureReason>
}