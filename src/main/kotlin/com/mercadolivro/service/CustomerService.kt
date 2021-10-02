package com.mercadolivro.service

import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.enums.Erros
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {


    fun getAll(name: String?, pageble: Pageable): Page<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(name, pageble)
        }
        return customerRepository.findAll(pageble);
    }


    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
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
            NotFoundException(
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