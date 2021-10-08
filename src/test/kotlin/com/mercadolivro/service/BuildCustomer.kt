package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Role
import com.mercadolivro.model.CustomerModel
import java.util.*

fun buildCustomer(
    id: Int? = null,
    name: String = "customer name",
    email: String = "${UUID.randomUUID()}@gmail.com",
    password: String = "password"
) = CustomerModel(
    id = id,
    name = name,
    email = email,
    status = CustomerStatus.ATIVO,
    password = password,
    roles = setOf(Role.CUSTOMER)
)