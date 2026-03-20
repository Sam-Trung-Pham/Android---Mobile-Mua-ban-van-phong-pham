package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryCacheRepository {
    suspend fun cacheListCategory(list: List<CategoryEntity>)

    suspend fun clearAllCacheListCategory()

    fun getListCategory(): Flow<List<CategoryEntity>>
}

//`feat: thêm interface CategoryCacheRepository cho lưu cache danh mục`