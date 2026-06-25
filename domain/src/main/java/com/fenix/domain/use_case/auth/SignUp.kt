package com.fenix.domain.use_case.auth

import com.fenix.domain.model.auth.Session
import com.fenix.domain.model.auth.SignUpInput
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.model.resource.bind
import com.fenix.domain.model.user.CreateUserInput
import com.fenix.domain.repository.auth.AuthRepository
import com.fenix.domain.repository.user.UserRepository
import javax.inject.Inject

class SignUp @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val currentAuthState: CurrentAuthState,
    private val getJwt: GetJwt,
    private val getAuthProviderId: GetAuthProviderId,
) {

    suspend operator fun invoke(input: SignUpInput): Resource<Session, FailureReason> {
        authRepository.signUp(input).bind { failure -> return failure }

        val authState = currentAuthState().bind { failure -> return failure }
        val jwt = getJwt().bind { failure -> return failure }
        val authProviderId = getAuthProviderId().bind { failure -> return failure }

        val user = userRepository.create(
            CreateUserInput(
                email = input.email,
                firstName = input.firstName,
                lastName = input.lastName,
                authProviderId = authProviderId
            )
        ).bind { failure ->
            return failure
        }

        return Resource.Success(
            Session(
                authState = authState,
                jwt = jwt,
                authProviderId = authProviderId,
                user = user,
            )
        )
    }
}