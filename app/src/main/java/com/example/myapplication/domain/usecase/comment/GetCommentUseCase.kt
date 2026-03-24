package com.example.myapplication.domain.usecase.comment

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.common.toListFeedBackEntity
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.usecase.feedback.CacheFeedbackUseCase
import com.example.myapplication.domain.usecase.feedback.ClearCacheFeedbackUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
class GetCommentUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val commentRepository: CommentRepository,

    private val clearCacheCommentUseCase: ClearCacheFeedbackUseCase,
    private val cacheFeedbackUseCase: CacheFeedbackUseCase
) {
    operator fun invoke() = flow {
        emit(UiState.Loading)
        //`feat: thêm GetCommentUseCase lấy bình luận và cập nhật cache phản hồi`

    }
}