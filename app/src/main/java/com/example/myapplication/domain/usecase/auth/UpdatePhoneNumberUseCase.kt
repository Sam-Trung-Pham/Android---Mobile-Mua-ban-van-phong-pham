package com.example.myapplication.domain.usecase.auth

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.model.dto.req.ReqUpdatePhoneDTO
import com.example.myapplication.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class UpdatePhoneNumberUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        id: String,
        req: ReqUpdatePhoneDTO
    ) = flow {
        emit(UiState.Loading)
        //`feat: thêm UpdatePhoneNumberUseCase xử lý cập nhật số điện thoại`
        try {
            when (val response = authRepository.updatePhoneNumber(id, req)) {
                is ResultWrapper.Success -> emit(UiState.Success(response.value))

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
        //`feat: bổ sung xử lý response và exception cho UpdatePhoneNumberUseCase`
    }
}
