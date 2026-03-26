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
        
    }
}
