package com.example.myapplication.domain.usecase.oder

import android.content.Context
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.model.dto.req.ReqCancelOrder
import com.example.myapplication.domain.repository.OrderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class CancelOrderUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val orderRepository: OrderRepository
) {
    operator fun invoke(
        id: String,
        req: ReqCancelOrder
    ) = flow {
        emit(UiState.Loading)
        //`feat: thêm CancelOrderUseCase xử lý hủy đơn hàng`
        try {
            when (val response = orderRepository.cancelOrder(id, req)) {
                is ResultWrapper.Success -> emit(UiState.Success(response.value))

                is ResultWrapper.GenericError -> {
                    Log.d("debug", response.message ?: "")

                    emit(UiState.Error(response.message?.ifEmpty {
                        context.getString(R.string.msg_wrong)
                    } ?: "Unknow Error"))
                }

                is ResultWrapper.NetworkError -> emit(UiState.Error("Network Error"))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(e.message ?: "Unknow Error"))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknow Error"))
        }
        //`feat: bổ sung xử lý hủy đơn hàng và bắt lỗi cho CancelOrderUseCase`
    }}