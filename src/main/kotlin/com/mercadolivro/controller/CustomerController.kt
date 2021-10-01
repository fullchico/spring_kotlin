package com.mercadolivro.controller

import com.mercadolivro.service.extension.toCustomeModel
import com.mercadolivro.service.request.PostCustomerRequest
import com.mercadolivro.service.request.PutCustomerRequest
import com.mercadolivro.service.response.CustomerResponse
import com.mercadolivro.service.extension.toResponse
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
) {
    
    @GetMapping()
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAll(name).map{
            it.toResponse()
        }
    }
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toCustomeModel())
    }
    
    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }
    
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomeModel(customerSaved))
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
    
}