package com.mercadolivro.service.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PostBookRequest(
    @field: NotEmpty(message = "Não pode ser vazio")
    var name: String,
    @field: Min(0, message = "Não pode ser menor que 0")
    var price: BigDecimal,
    
    @JsonAlias("customer_id")
    var customerId: Int
)
