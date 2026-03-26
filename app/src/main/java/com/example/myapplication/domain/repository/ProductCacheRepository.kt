package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductCacheRepository {
    suspend fun cacheListProduct(list: List<ProductEntity>)

    suspend fun clearAllCacheListProduct()

    fun getListProduct(): Flow<List<ProductEntity>>
}
//`feat: thêm interface ProductCacheRepository cho lưu cache sản phẩm`