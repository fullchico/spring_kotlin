package com.mercadolivro.service.execptionErros

data class FieldErrorResponse(
    var message: String,
    var field: String
)