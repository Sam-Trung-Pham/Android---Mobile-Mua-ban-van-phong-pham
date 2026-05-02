package com.example.myapplication.present.activity.auth.pass

import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.domain.model.dto.req.ReqResetPass
import com.example.myapplication.domain.model.dto.res.ResResetPass
import com.example.myapplication.domain.usecase.auth.ResetPassUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ResetPassViewModel @Inject constructor(
    private val resetPassUseCase: ResetPassUseCase
): BaseViewModel() {
    private val _uiState = MutableStateFlow<UiState<ResResetPass>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun handleResetPass(req: ReqResetPass, token: String) = launchIO {
        resetPassUseCase.invoke(req, token).collect { uiState ->
            _uiState.value = uiState
        }
    }
}
//`feat: thêm ResetPassViewModel xử lý luồng đặt lại mật khẩu`