package com.mercadolivro.exception

import com.mercadolivro.enums.Erros
import com.mercadolivro.exception.execptionErros.ErrorResponse
import com.mercadolivro.exception.execptionErros.FieldErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.sql.SQLIntegrityConstraintViolationException

@ControllerAdvice
class ControllerAdvice {
    
    @ExceptionHandler(NotFoundException::class)
    fun handleException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
    
    @ExceptionHandler(BadRequestException::class)
    fun handleException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
    
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            Erros.ML001.message,
            Erros.ML001.code,
            ex.bindingResult.fieldErrors.map {
                FieldErrorResponse(it.defaultMessage ?: "invalid", it.field)
            }
        )
        return ResponseEntity(error, HttpStatus.UNPROCESSABLE_ENTITY)
    }
    
    
    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handleInjectDependency(
        ex: SQLIntegrityConstraintViolationException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            Erros.ML301.message,
            Erros.ML301.code,
            null
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
    
    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(ex: AccessDeniedException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            Erros.ML000.message,
            Erros.ML000.code,
            null
        )
        return ResponseEntity(error, HttpStatus.FORBIDDEN)
    }
}