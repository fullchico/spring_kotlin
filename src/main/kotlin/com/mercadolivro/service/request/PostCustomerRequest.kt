package com.mercadolivro.service.request

import com.mercadolivro.exception.validation.EmailAvailble
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty


data class PostCustomerRequest(
    @field: NotEmpty(message = "Nome não pode ser nulo")
    var name: String,
    
    @field: Email(message = "E-mail deve ser valido")
    @EmailAvailble(message = "Email em uso")
    var email: String,
)