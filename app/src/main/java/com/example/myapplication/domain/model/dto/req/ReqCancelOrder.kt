package com.example.myapplication.domain.model.dto.req

data class ReqCancelOrder(
    val cancelReason: String = "",
    val status: String = ""
) {
}