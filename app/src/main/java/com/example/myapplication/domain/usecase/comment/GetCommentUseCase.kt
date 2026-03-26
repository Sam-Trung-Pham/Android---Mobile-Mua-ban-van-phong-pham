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
        try {
            when (val response = commentRepository.getComment()) {
                is ResultWrapper.Success -> {
                    clearCacheCommentUseCase.invoke().collect {
                        val data = response.value
                        cacheFeedbackUseCase.invoke(data.toListFeedBackEntity()).collect { }
                    }

                    emit(UiState.Success(response.value))
                }

                is ResultWrapper.GenericError -> emit(UiState.Error(response.message?.ifEmpty {
                    context.getString(R.string.msg_wrong)
                } ?: "Unknow Error"))

                is ResultWrapper.NetworkError -> emit(UiState.Error("Network Error"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
        //`feat: bổ sung xử lý lấy bình luận và cập nhật cache phản hồi cho GetCommentUseCase`
    }
}