package com.example.myapplication.present.activity.auth.pass

import com.example.myapplication.common.UiState
import com.example.myapplication.domain.model.dto.res.ResForgotPass

data class ForgotState(
    val uiState: UiState<ResForgotPass> = UiState.Idle
) {
}
//`feat: thêm ForgotState quản lý trạng thái quên mật khẩu`