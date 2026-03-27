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

}