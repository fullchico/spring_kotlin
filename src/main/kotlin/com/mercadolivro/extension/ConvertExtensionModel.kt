package com.mercadolivro.controller.extension

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomeModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomeModel(id: Int): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
}