package com.example.myapplication.domain.usecase.product

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.common.toListCacheProduct
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.repository.ProductRepository
import com.example.myapplication.domain.usecase.prod_cache.CacheProdUseCase
import com.example.myapplication.domain.usecase.prod_cache.ClearCacheProdUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.invoke

class FetchAllProductsUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val productRepository: ProductRepository,

    private val clearCacheProdUseCase: ClearCacheProdUseCase,
    private val cacheProdUseCase: CacheProdUseCase
) {
    operator fun invoke() = flow {
        emit(UiState.Loading) // show loading
        //`feat: thêm FetchAllProductsUseCase xử lý lấy danh sách sản phẩm và cập nhật cache`
        while (true) {
            try {
                // nhan ket qua tu api trar ve
                when (val response = productRepository.fetchAllProducts()) {
                    is ResultWrapper.Success -> {
                        val data = response.value.data
                        data?.let { listResponse ->
                            clearCacheProdUseCase.invoke().collect {
                                val listCache = listResponse.toListCacheProduct()
                                cacheProdUseCase.invoke(listCache).collect { }
                            }
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
    //`feat: bổ sung xử lý lấy sản phẩm, cập nhật cache và bắt lỗi cho FetchAllProductsUseCase`
}