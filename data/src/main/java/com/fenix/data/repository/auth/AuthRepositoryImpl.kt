package com.fenix.data.repository.auth

import com.fenix.domain.model.auth.Credentials
import com.fenix.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun signUp(credentials: Credentials): Boolean {
        return true
    }

    override suspend fun login(credentials: Credentials): Boolean {
        return true
    }
}