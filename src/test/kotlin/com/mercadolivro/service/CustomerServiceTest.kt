package com.mercadolivro.service

import com.mercadolivro.enums.Role
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.hibernate.id.UUIDGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    
    @MockK
    private lateinit var customerRepository: CustomerRepository
    
    @MockK
    private lateinit var bookService: BookService
    
    @MockK
    private lateinit var bCrypt: BCryptPasswordEncoder
    
    @InjectMockKs
    private lateinit var customerService: CustomerService
    
    
    @Test
    fun `should return all customers`() {
        val fakeCustomers = listOf(buildCustomer(), buildCustomer());
        
        every { customerRepository.findAll() } returns fakeCustomers
        
        val customers = customerService.getAll(null)
        
        assertEquals(fakeCustomers, customers)
        verify(exactly = 1) { customerRepository.findAll() }
        verify(exactly = 0) { customerRepository.findByNameContaining(any()) }
    }
    
    @Test
    fun `should return customers when name is informed`() {
        val name = UUID.randomUUID().toString()
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())
        
        every { customerRepository.findByNameContaining(name) } returns fakeCustomers
        
        val customers = customerService.getAll(name)
        
        assertEquals(fakeCustomers, customers)
        verify(exactly = 0) { customerRepository.findAll() }
        verify(exactly = 1) { customerRepository.findByNameContaining(name) }
    }
    
    @Test
    fun `show create customer and by password cripto`() {
        val initialPassword = Random().nextInt().toString()
        val fakeCustomer = buildCustomer(password = initialPassword)
        
        val encryptCode = UUID.randomUUID().toString()
        val fakeCustomerCopy = fakeCustomer.copy(password = encryptCode)
        
        every { customerRepository.save(fakeCustomerCopy) } returns fakeCustomerCopy
        every { bCrypt.encode(initialPassword) } returns encryptCode
        
        customerService.create(fakeCustomer)
        
        verify(exactly = 1) { customerRepository.save(fakeCustomerCopy) }
        verify(exactly = 1) { bCrypt.encode(initialPassword) }
    }
    
    
    @Test
    fun `should get customer by id`() {
        
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)
        
        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)
        
        val customer = customerService.findById(id)
        
        assertEquals(fakeCustomer, customer)
        verify(exactly = 1) { customerRepository.findById(id) }
    }
    
    @Test
    fun `should throw error get customer by id`() {
        
        val id = Random().nextInt()
        
        every { customerRepository.findById(id) } returns Optional.empty()
        
        val error = assertThrows<NotFoundException> { customerService.findById(id) }
        
        assertEquals("Customer [${id}] not exists", error.message)
        assertEquals("ML-201", error.errorCode)
        
        verify(exactly = 1) { customerRepository.findById(id) }
    }
    
    @Test
    fun `should update customer`() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)
        
        every { customerRepository.existsById(id) } returns true
        every { customerRepository.save(fakeCustomer) } returns fakeCustomer
        
        customerService.update(fakeCustomer)
        
        verify(exactly = 1) { customerRepository.existsById(id) }
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
        
    }
}