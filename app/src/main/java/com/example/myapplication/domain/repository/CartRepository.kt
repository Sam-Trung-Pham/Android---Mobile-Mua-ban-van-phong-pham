package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.entity.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun insertCart(c: CartEntity)
    suspend fun searchCartByIdProd(id: String): List<CartEntity>
    suspend fun updateCart(c: CartEntity)
    fun getAllCartEnable(): Flow<List<CartEntity>>
    suspend fun searchCartEnableByIdCart(id: Long): CartEntity?
    suspend fun deleteCart(c: CartEntity)
}

//`feat: thêm interface CartRepository cho quản lý giỏ hàng`