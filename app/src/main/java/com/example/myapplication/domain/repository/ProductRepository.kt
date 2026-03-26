package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.dto.res.ResProductDTO

interface ProductRepository {
    suspend fun fetchAllProducts(): ResultWrapper<ResProductDTO>
}
//`feat: thêm interface ProductRepository lấy danh sách sản phẩm`