package com.example.myapplication.domain.usecase.prod_cache

import com.example.myapplication.domain.repository.ProductCacheRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetListProductCacheUseCase @Inject constructor(
    private val productCacheRepository: ProductCacheRepository
) {
    operator fun invoke() = productCacheRepository.getListProduct()
}
//`feat: thêm GetListProductCacheUseCase lấy danh sách sản phẩm từ cache`