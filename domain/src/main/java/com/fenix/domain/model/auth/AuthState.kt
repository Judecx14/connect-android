package com.fenix.domain.model.auth

sealed class AuthState {
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
}