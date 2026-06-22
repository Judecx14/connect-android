package com.fenix.domain.use_case.user

import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.model.user.CreateUserProperties
import com.fenix.domain.model.user.User
import com.fenix.domain.repository.user.UserRepository
import javax.inject.Inject

class CreateUser @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(properties: CreateUserProperties) : Resource<User, FailureReason> {
        return userRepository.create(properties)
    }
}