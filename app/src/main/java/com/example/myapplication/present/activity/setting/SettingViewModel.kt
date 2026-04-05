package com.example.myapplication.present.activity.setting

import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.domain.model.dto.req.ReqUpdateAddressDTO
import com.example.myapplication.domain.model.dto.req.ReqUpdatePhoneDTO
import com.example.myapplication.domain.model.dto.res.ResLoginUserDTO
import com.example.myapplication.domain.model.dto.res.ResUpdatePhoneDTO
import com.example.myapplication.domain.usecase.auth.UpdateAddressUseCase
import com.example.myapplication.domain.usecase.auth.UpdatePhoneNumberUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val updatePhoneUseCase: UpdatePhoneNumberUseCase,
    private val updateAddressUseCase: UpdateAddressUseCase
) : BaseViewModel() {
    private val _stateUpdatePhone = MutableStateFlow<UiState<ResUpdatePhoneDTO>>(UiState.Idle)
    val stateUpdatePhone = _stateUpdatePhone.asStateFlow()

    private val _stateUpdateAddress = MutableStateFlow<UiState<ResUpdatePhoneDTO>>(UiState.Idle)
    val stateUpdateAddress = _stateUpdateAddress.asStateFlow()

    fun updatePhone(phone: String) = launchIO {
        val id =
            Gson().fromJson(SharedPrefCommon.jsonAcc, ResLoginUserDTO::class.java)?.user?.id ?: ""
        updatePhoneUseCase.invoke(
            id, ReqUpdatePhoneDTO(phone)
        ).collect { state ->
            _stateUpdatePhone.emit(state)
        }
    }
    //`feat: thêm SettingViewModel xử lý cập nhật số điện thoại và địa chỉ`

}