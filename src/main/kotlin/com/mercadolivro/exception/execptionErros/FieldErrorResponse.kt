package com.mercadolivro.exception.execptionErros

data class FieldErrorResponse(
    var message: String,
    var field: String
)