package com.example.myapplication.domain.usecase.prod_cache

import com.example.myapplication.domain.repository.ProductCacheRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClearCacheProdUseCase @Inject constructor(
    private val productCacheRepository: ProductCacheRepository
) {
    operator fun invoke() = flow {
        emit(productCacheRepository.clearAllCacheListProduct())
    }
}
//`feat: thêm ClearCacheProdUseCase xóa cache sản phẩm`