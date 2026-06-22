package com.fenix.data.model.user.response

import com.fenix.domain.model.user.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val email : String,
    val firstName : String,
    val lastName : String,
)

fun UserResponse.toUser() : User {
    return User(
        id = "1",
        name = this.firstName,
        email = this.email
    )
}


