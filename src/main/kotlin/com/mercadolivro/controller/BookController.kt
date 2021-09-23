package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
class BookController {
    @PostMapping
    fun create(@RequestBody request: PostBookRequest){

    }
}