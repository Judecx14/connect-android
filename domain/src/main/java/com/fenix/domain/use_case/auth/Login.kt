package com.fenix.domain.use_case.auth

import com.fenix.domain.model.auth.AuthCredentials
import com.fenix.domain.model.auth.Session
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.model.resource.bind
import com.fenix.domain.repository.auth.AuthRepository
import javax.inject.Inject


class Login @Inject constructor(
    private val authRepository: AuthRepository,
    private val currentAuthState: CurrentAuthState,
    private val getJwt: GetJwt,
    private val getAuthProviderId: GetAuthProviderId,
) {
    suspend operator fun invoke(
        credentials: AuthCredentials
    ): Resource<Session, FailureReason> {
        authRepository.login(credentials).bind { failure -> return failure }
        val authState = currentAuthState().bind { failure -> return failure }
        val jwt = getJwt().bind { failure -> return failure }
        val authProviderId = getAuthProviderId().bind { failure -> return failure }

        return Resource.Success(
            Session(
                authState = authState,
                jwt = jwt,
                authProviderId = authProviderId
            )
        )
    }
}