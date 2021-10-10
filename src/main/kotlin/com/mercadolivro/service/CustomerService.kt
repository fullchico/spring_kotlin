package com.mercadolivro.service

import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.enums.Erros
import com.mercadolivro.enums.Role
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCrypt: BCryptPasswordEncoder
) {
    
    
    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }
    
    
    fun create(customer: CustomerModel) {
        val customerCopy = customer.copy(
            roles = setOf(Role.CUSTOMER),
            password = bCrypt.encode(customer.password)
        )
        customerRepository.save(customerCopy)
    }
    
    
    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow {
            NotFoundException(
                Erros.ML201.message.format(id),
                Erros.ML201.code
            )
        }
    }
    
    
    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw NotFoundException(
                Erros.ML201.message.format(customer.id),
                Erros.ML201.code
            )
        }
        customerRepository.save(customer)
    }
    
    
    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        
        customer.status = CustomerStatus.INATIVO
        
        customerRepository.save(customer)
    }
    
    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}