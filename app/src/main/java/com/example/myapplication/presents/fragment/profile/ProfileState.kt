package com.example.myapplication.presents.fragment.profile

import com.datn.common.UiState
import com.datn.a.domain.model.dto.res.ResOrderDTO
import com.datn.a.domain.model.entity.CartEntity

data class ProfileState(
    val listCart: List<CartEntity> = emptyList(),
    val uiState: UiState<List<ResOrderDTO>> = UiState.Idle
) {
}