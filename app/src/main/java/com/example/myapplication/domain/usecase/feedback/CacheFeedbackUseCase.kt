package com.example.myapplication.domain.usecase.feedback

import com.example.myapplication.domain.model.entity.FeedbackEntity
import com.example.myapplication.domain.repository.FeedbackRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CacheFeedbackUseCase @Inject constructor(
    private val feedbackRepository: FeedbackRepository
) {
    operator fun invoke(list: List<FeedbackEntity>) = flow {
        emit(feedbackRepository.cacheListFeedBack(list))
    }
}
//`feat: thêm CacheFeedbackUseCase lưu danh sách phản hồi vào cache`