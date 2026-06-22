package com.fenix.domain.repository.auth

import com.fenix.domain.model.auth.AuthState
import com.fenix.domain.model.auth.Credentials

interface AuthRepository {
    suspend fun signUp(credentials: Credentials): String?
    suspend fun login(credentials: Credentials): Boolean
    fun logout(): Boolean
    fun currentAuthState(): AuthState
}