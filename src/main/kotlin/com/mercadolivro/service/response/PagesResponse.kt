package com.mercadolivro.service.response

class PagesResponse<T>(
    var items: List<T>,
    var currentPage: Int,
    var totalItems: Long,
    var totalPages: Int
) {
}