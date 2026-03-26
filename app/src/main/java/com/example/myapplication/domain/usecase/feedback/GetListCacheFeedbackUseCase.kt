package com.example.myapplication.domain.usecase.feedback

import com.example.myapplication.domain.repository.FeedbackRepository
import javax.inject.Inject

class GetListCacheFeedbackUseCase @Inject constructor(
    private val feedbackRepository: FeedbackRepository
) {
    operator fun invoke() = feedbackRepository.getListFeedBack()
}
//`feat: thêm GetListCacheFeedbackUseCase lấy danh sách phản hồi từ cache`