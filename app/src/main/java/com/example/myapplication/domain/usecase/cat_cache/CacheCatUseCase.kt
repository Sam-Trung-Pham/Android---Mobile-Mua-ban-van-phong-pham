package com.example.myapplication.domain.usecase.cat_cache

import com.example.myapplication.domain.model.entity.CategoryEntity
import com.example.myapplication.domain.repository.CategoryCacheRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CacheCatUseCase @Inject constructor(
    private val categoryCacheRepository: CategoryCacheRepository
) {
    operator fun invoke(list: List<CategoryEntity>) = flow {
        emit(categoryCacheRepository.cacheListCategory(list))
    }
}
//`feat: thêm CacheCatUseCase lưu danh mục vào cache`