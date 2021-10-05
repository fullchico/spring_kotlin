package com.mercadolivro.service.request

data class LoginRequest(
    var email: String,
    var password: String
)