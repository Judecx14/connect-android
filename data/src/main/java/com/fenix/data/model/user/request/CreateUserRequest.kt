package com.fenix.data.model.user.request

import com.fenix.domain.model.user.CreateUserProperties
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val authProviderId: String
)

internal fun CreateUserProperties.toRequest() : CreateUserRequest {
    return CreateUserRequest(
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        authProviderId = this.authProviderId
    )
}