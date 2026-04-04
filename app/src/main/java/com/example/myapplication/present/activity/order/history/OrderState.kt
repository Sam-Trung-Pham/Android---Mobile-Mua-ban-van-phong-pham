package com.example.myapplication.present.activity.order.history

import com.example.myapplication.common.UiState
import com.example.myapplication.domain.model.dto.res.ResOrderDTO
import com.example.myapplication.domain.model.entity.CommentEntity

data class OrderState(
    val uiState: UiState<List<ResOrderDTO>> = UiState.Idle,
    val listCommentCaches: List<CommentEntity> = emptyList()
) {
}
//`feat: thêm OrderState quản lý trạng thái và danh sách bình luận đơn hàng`