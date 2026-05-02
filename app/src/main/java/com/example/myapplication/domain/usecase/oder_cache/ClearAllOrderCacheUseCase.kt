package com.example.myapplication.domain.usecase.oder_cache

import com.example.myapplication.domain.repository.OrderCacheRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClearAllOrderCacheUseCase @Inject constructor(
    private val orderCacheRepository: OrderCacheRepository
) {
    operator fun invoke() = flow {
        emit(orderCacheRepository.clearCacheOrder())
    }
}
//`feat: thêm ClearAllOrderCacheUseCase xóa toàn bộ cache đơn hàng`