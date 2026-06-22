package com.fenix.domain.repository.customer

import com.fenix.domain.model.customer.Customer

interface CustomerRepository {
    fun getCustomer(): Customer
}