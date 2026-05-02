package com.example.myapplication.domain.usecase.auth

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.model.dto.req.ReqSignUpUserDTO
import com.example.myapplication.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val authRepository: AuthRepository
) {
    operator fun invoke(req: ReqSignUpUserDTO) = flow {
        emit(UiState.Loading)
        //`feat: thêm SignUpUserUseCase xử lý đăng ký người dùng`
        try {
            when (val response = authRepository.signUpUser(req)) {
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
        //`feat: bổ sung xử lý response và exception cho SignUpUserUseCase`
    }
}