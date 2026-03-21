package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.entity.FeedbackEntity
import kotlinx.coroutines.flow.Flow

interface FeedbackRepository {
    suspend fun cacheListFeedBack(list: List<FeedbackEntity>)

    suspend fun clearCacheFeedBack()

    fun getListFeedBack(): Flow<List<FeedbackEntity>>
}
//`feat: thêm interface FeedbackRepository cho lưu cache phản hồi`