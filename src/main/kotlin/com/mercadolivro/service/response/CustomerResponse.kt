package com.mercadolivro.service.response

import com.mercadolivro.service.enums.CustomerStatus

data class CustomerResponse(
    var id: Int? = null,
    
    var name: String,
    
    var email: String,
    
    var status: CustomerStatus
) {


}
