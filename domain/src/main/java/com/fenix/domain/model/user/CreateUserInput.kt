package com.fenix.domain.model.user

data class CreateUserInput(
    val email : String,
    val firstName : String,
    val lastName : String,
    val authProviderId: String
)