package com.fenix.domain.model.user

data class CreateUserProperties(
    val email : String,
    val firstName : String,
    val lastName : String,
    val authProviderId: String
)