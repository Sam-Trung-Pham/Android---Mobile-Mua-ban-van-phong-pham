package com.example.myapplication.present.activity.comment.reviews

import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.domain.usecase.feedback.GetListCacheFeedbackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    getAllFeedbackUseCase: GetListCacheFeedbackUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(ReviewState())
    val state = combine(
        _state,
        getAllFeedbackUseCase.invoke()
    ) { state, list ->
        state.copy(
            listComment = list
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 5_000
        ), initialValue = ReviewState()
    )
}
//`feat: thêm ReviewViewModel quản lý trạng thái danh sách đánh giá`