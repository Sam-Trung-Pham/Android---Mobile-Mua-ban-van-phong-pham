package com.example.myapplication.domain.usecase.cat_cache

import com.example.myapplication.domain.repository.CategoryCacheRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClearCacheCatUseCase @Inject constructor(
    private val categoryCacheRepository: CategoryCacheRepository
) {
    operator fun invoke() = flow {
        emit(categoryCacheRepository.clearAllCacheListCategory())
    }
}
//`feat: thêm ClearCacheCatUseCase xóa cache danh mục`