package com.example.myapplication.present.activity.auth.si

import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.domain.model.dto.req.ReqLoginUserDTO
import com.example.myapplication.domain.usecase.auth.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.invoke

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : BaseViewModel() {
    private val _passwordInput = MutableStateFlow("")
    private val _emailInput = MutableStateFlow("")
    private val _state = MutableStateFlow(SignInState())
    val state = combine(
        _state, _emailInput, _passwordInput,
    ) { state, email, password ->
        state.copy(
            email = email,
            password = password
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 5000
        ), initialValue = SignInState()
    )
    //`feat: thêm SignInViewModel quản lý trạng thái và xử lý đăng nhập`
