package com.example.myapplication.present.activity.order.confirm

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.domain.model.dto.req.ReqCheckOutDTO
import com.example.myapplication.domain.model.dto.req.ReqProdCheckOut
import com.example.myapplication.domain.model.dto.res.ResCheckOutDTO
import com.example.myapplication.domain.model.dto.res.ResLoginUserDTO
import com.example.myapplication.domain.usecase.order.CheckOutOrderUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val checkOutOrderUseCase: CheckOutOrderUseCase
): BaseViewModel() {
    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private val _stateCheckOut = MutableStateFlow<UiState<ResCheckOutDTO>>(UiState.Idle)
    val stateCheckOut = _stateCheckOut.asStateFlow()

    fun setMessage(str: String) {
        _message.value = str
    }
    //`feat: thêm ConfirmViewModel quản lý trạng thái xác nhận đơn hàng`

}