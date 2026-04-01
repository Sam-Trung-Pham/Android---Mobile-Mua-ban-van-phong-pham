package com.example.myapplication.present.activity.auth.su

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.domain.model.dto.req.ReqLoginUserDTO
import com.example.myapplication.domain.model.dto.req.ReqSignUpUserDTO
import com.example.myapplication.domain.usecase.auth.SignUpUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.invoke

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: SignUpUserUseCase
) : BaseViewModel() {
    private val _usernameValue = MutableStateFlow("")
    private val _emailValue = MutableStateFlow("")
    private val _passwordValue = MutableStateFlow("")
    private val _confirmPasswordValue = MutableStateFlow("")
    private val _state = MutableStateFlow(SignUpState())
    val state = combine(
        _state,
        _usernameValue,
        _emailValue,
        _passwordValue,
        _confirmPasswordValue
    )
    { state, username, email, password, confirmPass ->
        state.copy(
            username = username,
            email = email,
            password = password,
            confirmPassword = confirmPass
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 5000
        ), initialValue = SignUpState()
    )
//    `feat: thêm SignUpViewModel quản lý trạng thái và dữ liệu đăng ký`
fun changeEmailValue(value: String) {
    _emailValue.value = value
}

    fun changePasswordValue(value: String) {
        _passwordValue.value = value
    }

    fun changeUsernameValue(value: String) {
        _usernameValue.value = value
    }

    fun changeConfirmPasswordValue(value: String) {
        _confirmPasswordValue.value = value
    }

    fun changeStateToIdle() {
        _state.value = _state.value.copy(
            uiState = UiState.Idle
        )
    }
//    `feat: bổ sung cập nhật dữ liệu nhập và reset trạng thái trong SignUpViewModel`
fun onSignUpEvent() {
    val req = ReqSignUpUserDTO(
        username = _usernameValue.value,
        email = _emailValue.value,
        password = _passwordValue.value,
    )
    Log.d("debug", "$req")
    loginUser(req)
}

    private fun loginUser(req: ReqSignUpUserDTO) = viewModelScope.launch {
        signUpUserUseCase.invoke(req).collect { uiState ->
            _state.value = _state.value.copy(
                uiState = uiState
            )
        }
    }
//    `feat: bổ sung xử lý sự kiện đăng ký và gọi API đăng ký trong SignUpViewModel`
}