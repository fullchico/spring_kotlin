package com.mercadolivro.controller

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("admin")
class AdminController(

) {
    
    @GetMapping("/report")
    fun report(): String {
        return "This is a Repost. Only Admin can see it!"
    }
    
}