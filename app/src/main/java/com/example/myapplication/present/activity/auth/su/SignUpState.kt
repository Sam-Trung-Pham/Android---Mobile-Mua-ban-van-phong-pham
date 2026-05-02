package com.example.myapplication.present.activity.auth.su
import com.example.myapplication.common.UiState
import com.example.myapplication.domain.model.dto.res.ResSignUpUserDTO
data class SignUpState(
    val uiState: UiState<ResSignUpUserDTO> = UiState.Idle,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
) {
}
//`feat: thêm SignUpState quản lý trạng thái và dữ liệu đăng ký`