package com.fenix.domain.use_case.auth

import com.fenix.domain.model.auth.AuthCredentials
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.repository.auth.AuthRepository
import javax.inject.Inject


class Login @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        credentials: AuthCredentials
    ) : Resource<String, FailureReason> {
        val result = authRepository.login(credentials)

        return result
    }
}