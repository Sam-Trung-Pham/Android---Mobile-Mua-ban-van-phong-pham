package com.example.myapplication.present.activity.favorite

import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.domain.usecase.favorite.GetAllFavoriteUseCase
import com.example.myapplication.domain.usecase.product.FetchAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    fetchAllProductsUseCase: FetchAllProductsUseCase,
    getAllFavoriteUseCase: GetAllFavoriteUseCase
) : BaseViewModel() {
    private val _state = MutableStateFlow(FavoriteState())
    val state = combine(
        _state,
        fetchAllProductsUseCase.invoke(),
        getAllFavoriteUseCase.invoke()
    ) { state, uiState, listFavorite ->
        state.copy(
            uiState = uiState,
            listFavorite = listFavorite
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FavoriteState()
    )

    fun changeStateToIdle() {
        _state.value = _state.value.copy(uiState = UiState.Idle)
    }
}
//`feat: thêm FavoriteViewModel quản lý trạng thái sản phẩm yêu thích`