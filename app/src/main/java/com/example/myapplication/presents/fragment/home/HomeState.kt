package com.example.myapplication.presents.fragment.home

import com.datn.common.UiState
import com.datn.domain.model.dto.res.ResCatDTO
import com.datn.domain.model.dto.res.ResProductDTO

data class HomeState(
    var productState: UiState<ResProductDTO> = UiState.Idle,
    var catState: UiState<List<ResCatDTO>> = UiState.Idle
) {
}