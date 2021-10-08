package com.mercadolivro.service.extension

import com.mercadolivro.service.response.PagesResponse
import org.springframework.data.domain.Page


fun <T> Page<T>.toPageResponse(): PagesResponse<T> {
    return PagesResponse(
        this.content,
        this.number,
        this.totalElements,
        this.totalPages
    )
}