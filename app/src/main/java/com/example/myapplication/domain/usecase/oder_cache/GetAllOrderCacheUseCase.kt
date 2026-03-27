package com.example.myapplication.domain.usecase.oder_cache

import com.example.myapplication.domain.repository.OrderCacheRepository
import javax.inject.Inject

class GetAllOrderCacheUseCase @Inject constructor(
    private val orderCacheRepository: OrderCacheRepository
) {
    operator fun invoke() = orderCacheRepository.getAllCacheOrder()
}
//`feat: thêm GetAllOrderCacheUseCase lấy toàn bộ đơn hàng từ cache`