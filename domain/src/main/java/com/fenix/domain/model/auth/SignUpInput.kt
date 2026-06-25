package com.fenix.domain.model.auth

data class SignUpInput(
    val email: String,
    val password: String,
    val firstName : String,
    val lastName : String,
)