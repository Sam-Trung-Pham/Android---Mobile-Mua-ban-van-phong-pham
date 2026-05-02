package com.example.myapplication.presents.fragment.profile

import androidx.lifecycle.viewModelScope
import com.datn.a.common.UiState
import com.datn.a.common.base.BaseViewModel
import com.datn.a.data.storage.SharedPrefCommon
import com.datn.a.domain.model.dto.res.ResLoginUserDTO
import com.datn.a.domain.usecase.cart.GetAllCartsUseCase
import com.datn.a.domain.usecase.order.GetAllOrderByIdUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    getAllCartUseCase: GetAllCartsUseCase,
    getAllOrderByIdUseCase: GetAllOrderByIdUseCase,
) : BaseViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = combine(
        _state,
        getAllCartUseCase.invoke(),
        getAllOrderByIdUseCase.invoke(
            Gson().fromJson(SharedPrefCommon.jsonAcc, ResLoginUserDTO::class.java)?.user?.id ?: ""
        )
    ) { state, listCartEntity, listResOrder ->
        state.copy(
            listCartEntity,
            listResOrder
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 5000
        ), initialValue = ProfileState()
    )

    fun changeStateToIdle() {
        _state.value = _state.value.copy(
            uiState = UiState.Idle
        )
    }
}