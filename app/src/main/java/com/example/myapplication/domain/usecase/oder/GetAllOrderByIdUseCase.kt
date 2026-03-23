package com.example.myapplication.domain.usecase.oder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.asLiveData
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.UiState
import com.example.myapplication.common.toListOrderEntity
import com.example.myapplication.common.toListResOrderDTO
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.repository.OrderCacheRepository
import com.example.myapplication.domain.repository.OrderRepository
import com.example.myapplication.domain.usecase.order_cache.CacheListOrderUseCase
import com.example.myapplication.domain.usecase.order_cache.ClearAllOrderCacheUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class GetAllOrderByIdUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val orderRepository: OrderRepository,

    private val orderCacheRepository: OrderCacheRepository,

    private val clearAllOrderCacheUseCase: ClearAllOrderCacheUseCase,
    private val cacheListOrderUseCase: CacheListOrderUseCase,
) {
    operator fun invoke(id: String) = flow {
        emit(UiState.Loading)

        while (true) {
            try {
                when (val response = orderRepository.getOrdersByUser(id)) {
                    is ResultWrapper.Success -> {
                        val list = orderCacheRepository.getAllCacheOrder().firstOrNull() ?: emptyList()
                        Log.d("sampt", "Check List: ${list.size}\n${orderCacheRepository.getAllCacheOrder().asLiveData().value == null}")
                        val listTemp = list.toListResOrderDTO()
                            .filter { it.status == AppConst.STATUS_ORDER_TO_RECEIVE }
                        val data = response.value.toListOrderEntity()
                        Log.d("sampt", "Size: ${listTemp.size}\n${data.filter { it.userId == id }.filter { it.status == AppConst.STATUS_ORDER_TO_RECEIVE }.size}")
                        if (listTemp.size < data.filter { it.userId == id }.filter { it.status == AppConst.STATUS_ORDER_TO_RECEIVE }.size) { // có đơn hàng xác nhận mới  -> push noti
                            createChannel(context)
                            showNotification(context)
                        }
                        clearAllOrderCacheUseCase.invoke().collect {
                            cacheListOrderUseCase.invoke(data).collect {}
                        }
                        emit(UiState.Success(response.value))
                    }
                    is ResultWrapper.GenericError -> emit(UiState.Error(response.message?.ifEmpty {
                        context.getString(R.string.msg_wrong)
                    } ?: "Unknow Error"))
                    is ResultWrapper.NetworkError -> emit(UiState.Error("Network Error"))
                }
            } catch (e: HttpException) {
                emit(UiState.Error(e.message ?: "Unknow Error"))
            } catch (e: Exception) {
                emit(UiState.Error(e.message ?: "Unknow Error"))
            }
            delay(5_000)
        }
    }.flowOn(Dispatchers.IO)
    //`feat: thêm GetAllOrderByIdUseCase lấy đơn hàng theo người dùng và đồng bộ cache`
    
}