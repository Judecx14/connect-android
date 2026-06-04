package com.fenix.domain.use_case.auth

import com.fenix.domain.model.auth.Credentials
import com.fenix.domain.repository.auth.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(credentials: Credentials) : Boolean {
        val result = authRepository.signUp(credentials)

        return result
    }
 }