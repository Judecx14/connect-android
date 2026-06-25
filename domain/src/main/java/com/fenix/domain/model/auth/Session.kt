package com.fenix.domain.model.auth

import com.fenix.domain.model.user.User

data class Session(
    val authState: AuthState,
    val jwt: String,
    val authProviderId: String,
    val user: User?
)