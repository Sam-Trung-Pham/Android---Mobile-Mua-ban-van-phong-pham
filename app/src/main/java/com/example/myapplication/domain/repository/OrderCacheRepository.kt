package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

interface OrderCacheRepository {
    suspend fun cacheListOrder(list: List<OrderEntity>)

    fun getAllCacheOrder(): Flow<List<OrderEntity>>

    suspend fun clearCacheOrder()

    suspend fun deleteOrderById(id: String)
}
//`feat: thêm interface OrderCacheRepository cho lưu cache đơn hàng`