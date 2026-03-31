package com.example.myapplication.present.activity.auth.si

import com.example.myapplication.common.UiState
import com.example.myapplication.domain.model.dto.res.ResLoginUserDTO

data class SignInState(
    val uiState: UiState<ResLoginUserDTO> = UiState.Idle,
    val email: String = "",
    val password: String = ""
) {
}
//`feat: thêm SignInState quản lý trạng thái và dữ liệu đăng nhập`