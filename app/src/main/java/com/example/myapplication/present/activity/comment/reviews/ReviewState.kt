package com.example.myapplication.present.activity.comment.reviews

import com.example.myapplication.domain.model.entity.FeedbackEntity

data class ReviewState(
    val listComment: List<FeedbackEntity> = emptyList()
)
//`feat: thêm ReviewState quản lý danh sách đánh giá sản phẩm`