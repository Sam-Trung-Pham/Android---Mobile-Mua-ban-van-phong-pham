package com.example.myapplication.present.activity.favorite

import com.example.myapplication.common.UiState
import com.example.myapplication.domain.model.dto.res.ResProductDTO
import com.example.myapplication.domain.model.entity.FavoriteEntity

data class FavoriteState(
    val uiState: UiState<ResProductDTO> = UiState.Idle,
    val listFavorite: List<FavoriteEntity> = emptyList()
) {
}
//`feat: thêm FavoriteState quản lý trạng thái và danh sách sản phẩm yêu thích`