package com.fenix.domain.model.customer

import com.fenix.domain.model.user.User
import com.fenix.domain.model.user.UserProperties

data class Customer(
    val user: User,
) : UserProperties by user