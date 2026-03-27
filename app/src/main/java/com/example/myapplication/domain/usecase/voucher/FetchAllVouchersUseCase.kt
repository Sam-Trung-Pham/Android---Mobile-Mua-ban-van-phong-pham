package com.example.myapplication.domain.usecase.voucher

import android.content.Context
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.repository.VoucherRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class FetchAllVouchersUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val voucherRepository: VoucherRepository
) {
    operator fun invoke() = flow {
        emit(UiState.Loading)
    //`feat: thêm FetchAllVouchersUseCase xử lý lấy danh sách mã giảm giá`

    }
}