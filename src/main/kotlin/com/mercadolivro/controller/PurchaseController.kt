package com.mercadolivro.controller

import com.mercadolivro.mapper.PurchaseMapper
import com.mercadolivro.service.PurchaseService
import com.mercadolivro.service.request.PostPurchaseRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("purchases")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPurchase(@RequestBody request: PostPurchaseRequest) {
        purchaseService.create(purchaseMapper.toModel(request))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listerAllPurchase(){

    }
}