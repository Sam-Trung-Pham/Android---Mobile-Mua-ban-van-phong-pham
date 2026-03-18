package com.example.myapplication.domain.model.dto.req

data class ReqCommentDTO(
    val userId: String = "",
    val productId: List<String> = emptyList(),
    val content: String = "",
    val rating: Int = 0
){
}