package com.mercadolivro.service.enums

enum class Erros(val code: String, val message: String) {
    ML101("ML-101","Book [%s] not exists"),
    ML102("ML-102","Cannot update book with status [%s]"),
    ML201("ML-201","Customer [%s] not exists"),
    
}