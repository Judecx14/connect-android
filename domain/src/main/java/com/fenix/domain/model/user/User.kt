package com.fenix.domain.model.user

interface UserProperties {
    val id: String?
    val name: String
    val email: String
}

data class User(
    override val id: String?,
    override val name: String,
    override val email: String
) : UserProperties
