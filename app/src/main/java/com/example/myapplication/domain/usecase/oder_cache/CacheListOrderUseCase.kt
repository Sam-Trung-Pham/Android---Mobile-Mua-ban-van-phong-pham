package com.example.myapplication.domain.usecase.oder_cache

import com.example.myapplication.domain.model.entity.OrderEntity
import com.example.myapplication.domain.repository.OrderCacheRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CacheListOrderUseCase @Inject constructor(
    private val orderCacheRepository: OrderCacheRepository
) {
    operator fun invoke(list: List<OrderEntity>) = flow {
        emit(orderCacheRepository.cacheListOrder(list))
    }
}
//`feat: thêm CacheListOrderUseCase lưu danh sách đơn hàng vào cache`