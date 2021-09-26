package com.mercadolivro.controller.response

import com.mercadolivro.model.CustomerModel
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

data class BookResponse(
    
    var id: Int? = null,
    
    var name: String,

    var price: BigDecimal,
    
    var customer: CustomerModel? = null
) {

}
