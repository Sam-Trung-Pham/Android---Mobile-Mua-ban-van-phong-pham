package com.example.myapplication.domain.usecase.oder

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.repository.OrderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class GetAllOrderUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val orderRepository: OrderRepository
) {
    operator fun invoke() = flow {
        emit(UiState.Loading)
        //feat: thêm GetAllOrderUseCase lấy toàn bộ đơn hàng
        try {
            when (val response = orderRepository.getAllOrder()) {
                is ResultWrapper.Success -> {
                    val data = response.value

                    emit(UiState.Success(response.value))
                }

                is ResultWrapper.GenericError ->
                    emit(UiState.Error(response.message?.ifEmpty {
                        context.getString(R.string.msg_wrong)
                    } ?: "Unknow Error"))

                is ResultWrapper.NetworkError -> emit(UiState.Error("Network Error"))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(e.message ?: "Unknow Error"))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknow Error"))
        }
        //`feat: bổ sung xử lý lấy toàn bộ đơn hàng và bắt lỗi cho GetAllOrderUseCase`
    }.flowOn(Dispatchers.IO)
}