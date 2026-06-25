package com.fenix.domain.use_case.auth

import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.repository.auth.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Resource<Unit, FailureReason> {
        return authRepository.logout()
    }
}