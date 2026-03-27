package com.example.myapplication.domain.usecase.prod_cache

import com.example.myapplication.domain.model.entity.ProductEntity
import com.example.myapplication.domain.repository.ProductCacheRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CacheProdUseCase @Inject constructor(
    private val productCacheRepository: ProductCacheRepository
) {
    operator fun invoke(list: List<ProductEntity>) = flow {
        emit(productCacheRepository.cacheListProduct(list))
    }
}
//`feat: thêm CacheProdUseCase lưu danh sách sản phẩm vào cache`