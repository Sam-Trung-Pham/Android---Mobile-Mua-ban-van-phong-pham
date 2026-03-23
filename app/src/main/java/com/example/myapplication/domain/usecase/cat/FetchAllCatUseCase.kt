package com.example.myapplication.domain.usecase.cat

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.common.toListCategoryEntities
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.repository.CatRepository
import com.example.myapplication.domain.usecase.cat_cache.CacheCatUseCase
import com.example.myapplication.domain.usecase.cat_cache.ClearCacheCatUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class FetchAllCatUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val catRepository: CatRepository,

    private val cacheCatUseCase: CacheCatUseCase,
    private val clearCacheCatUseCase: ClearCacheCatUseCase
) {
    operator fun invoke() = flow {
        emit(UiState.Loading)
        //`feat: thêm FetchAllCatUseCase xử lý lấy danh sách danh mục và cập nhật cache`
        try {
            when (val response = catRepository.fetchAllCat()) {
                is ResultWrapper.Success -> {
                    clearCacheCatUseCase.invoke().collect {
                        cacheCatUseCase.invoke(response.value.toListCategoryEntities()).collect { }
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
        //`feat: bổ sung xử lý lấy danh mục và cập nhật cache cho FetchAllCatUseCase`
    }}