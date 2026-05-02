package com.example.myapplication.present.activity.search

import com.example.myapplication.common.UiState
import com.example.myapplication.domain.model.dto.res.ResProductDTO

data class SearchState(
    var productState: UiState<ResProductDTO> = UiState.Idle,
) {
}
//`feat: thêm SearchState quản lý trạng thái dữ liệu tìm kiếm sản phẩm`