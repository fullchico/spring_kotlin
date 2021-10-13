package com.mercadolivro.repository

import com.mercadolivro.service.buildCustomer
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerRepositoryTest {
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setup() {
        customerRepository.deleteAll()
    }

    @Test
    fun `should return name containing`() {
        val marcos = customerRepository.save(buildCustomer(name = "Marcos"))
        val matheus = customerRepository.save(buildCustomer(name = "Matheus"))

        val customer = customerRepository.findByNameContaining("Ma")

        assertEquals(listOf(marcos, matheus), customer)

    }

    @Nested
    inner class `exists by email`() {
        @Test
        fun `should return true when email exists`() {
            val email = "email@teste.com"
            customerRepository.save(buildCustomer(email = email))
            val results = customerRepository.existsByEmail(email)

            assertTrue(results)
        }

        @Test
        fun `should return false when email do not exists`() {
            val email = "email@teste.com"
            val results = customerRepository.existsByEmail(email)

            assertFalse(results)
        }
    }
}