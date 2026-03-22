package com.example.myapplication.domain.usecase.feedback

import com.example.myapplication.repository.FeedbackRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClearCacheFeedbackUseCase @Inject constructor(
    private val feedbackRepository: FeedbackRepository
) {
    operator fun invoke() = flow {
        emit(feedbackRepository.clearCacheFeedBack())
    }
}
//`feat: thêm ClearCacheFeedbackUseCase xóa cache phản hồi`