package com.fenix.domain.repository.user

import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.model.user.CreateUserInput
import com.fenix.domain.model.user.User

interface UserRepository {
    suspend fun create(input: CreateUserInput) : Resource<User, FailureReason>
}