package com.example.myapplication.present.activity.voucher

import com.example.myapplication.common.UiState
import com.example.myapplication.domain.model.dto.res.ResVoucherDTO

data class VoucherState(
    val uiState: UiState<List<ResVoucherDTO>> = UiState.Idle
) {
}
//`feat: thêm VoucherState quản lý trạng thái danh sách mã giảm giá`