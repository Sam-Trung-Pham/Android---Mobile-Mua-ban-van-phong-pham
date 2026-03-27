package com.example.myapplication.domain.usecase.oder

import android.content.Context
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.model.dto.req.ReqUpdateOrder
import com.example.myapplication.domain.repository.OrderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class UpdateOrderUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val orderRepository: OrderRepository
) {
    operator fun invoke(
        idOrder: String,
        reqUpdateOrder: ReqUpdateOrder
    ) = flow {
        emit(UiState.Loading)
        //`feat: thêm UpdateOrderUseCase xử lý cập nhật đơn hàng`

    }
}