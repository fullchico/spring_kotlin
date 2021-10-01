package com.mercadolivro.exception.validation

import com.mercadolivro.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailbleValidator(var customerService: CustomerService) : ConstraintValidator<EmailAvailble, String> {
    
    
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrBlank()) {
            return false
        }
        return customerService.emailAvailable(value)
    }
}