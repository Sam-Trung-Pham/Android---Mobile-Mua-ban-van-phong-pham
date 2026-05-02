package com.example.myapplication.domain.usecase.oder_cache

import com.example.myapplication.domain.repository.OrderCacheRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteOrderByIdUseCase @Inject constructor(
    private val orderCacheRepository: OrderCacheRepository
) {
    operator fun invoke(id: String) = flow {
        orderCacheRepository.deleteOrderById(id)

        emit(true)
    }
}
//`feat: thêm DeleteOrderByIdUseCase xóa đơn hàng khỏi cache theo id`