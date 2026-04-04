package com.example.myapplication.present.activity.order.history

import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.domain.model.dto.req.ReqCancelOrder
import com.example.myapplication.domain.model.dto.req.ReqCommentDTO
import com.example.myapplication.domain.model.dto.req.ReqUpdateOrder
import com.example.myapplication.domain.model.dto.res.ResLoginUserDTO
import com.example.myapplication.domain.model.dto.res.ResUpdateOrder
import com.example.myapplication.domain.model.entity.CommentEntity
import com.example.myapplication.domain.usecase.comment.CacheCommentUseCase
import com.example.myapplication.domain.usecase.comment.CreateCommentUseCase
import com.example.myapplication.domain.usecase.comment.GetAllCommentByIdUseCase
import com.example.myapplication.domain.usecase.order.CancelOrderUseCase
import com.example.myapplication.domain.usecase.order.GetAllOrderByIdUseCase
import com.example.myapplication.domain.usecase.order.UpdateOrderUseCase
import com.example.myapplication.domain.usecase.order_cache.DeleteOrderByIdUseCase
import com.example.myapplication.domain.usecase.order_cache.GetAllOrderCacheUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    getAllOrderByIdUseCase: GetAllOrderByIdUseCase,
    getAllCommentByIdUserUseCase: GetAllCommentByIdUseCase,
    private val updateOrderUseCase: UpdateOrderUseCase,
    private val cacheCommentUseCase: CacheCommentUseCase,
    private val createCommentUseCase: CreateCommentUseCase,
    private val cancelOrderUseCase: CancelOrderUseCase,

    private val getAllOrderCacheUseCase: GetAllOrderCacheUseCase,
    private val deleteOrderByIdUseCase: DeleteOrderByIdUseCase
) : BaseViewModel() {
    val order = getAllOrderCacheUseCase.invoke()

    private val _stateComment = MutableStateFlow<UiState<Any>>(UiState.Idle)
    val stateComment = _stateComment.asStateFlow()

    val listOrder = getAllOrderCacheUseCase.invoke()

    private val _uiStateUpdate: MutableStateFlow<UiState<ResUpdateOrder>> =
        MutableStateFlow(UiState.Idle)
    val uiStateUpdate = _uiStateUpdate.asStateFlow()

    private val idUserCurrent =
        Gson().fromJson(SharedPrefCommon.jsonAcc, ResLoginUserDTO::class.java)?.user?.id ?: ""

    private val _state = MutableStateFlow(OrderState())
    val state = combine(
        _state,
        getAllOrderByIdUseCase.invoke(idUserCurrent),
        getAllCommentByIdUserUseCase.invoke(idUserCurrent)
    ) { state, uiState, commentsCache ->
        state.copy(
            uiState = uiState,
            listCommentCaches = commentsCache
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 5_000,
        ), initialValue = OrderState()
    )
    //`feat: thêm OrderViewModel quản lý trạng thái và thao tác đơn hàng`
    fun changeStateToIdle() {
        _state.value = _state.value.copy(
            uiState = UiState.Idle
        )
    }

    fun updateOrderUseCase(
        orderId: String,
        status: String
    ) = launchIO {
        updateOrderUseCase.invoke(
            orderId, ReqUpdateOrder(status)
        ).collect { uiState ->
            _uiStateUpdate.value = uiState
        }
    }

    fun cancelOrderUseCase(
        orderId: String,
        status: String,
        reason: String
    ) = launchIO {
        cancelOrderUseCase.invoke(
            orderId,
            ReqCancelOrder(
                reason, status
            )
        ).collect { uiState ->
            deleteOrderByIdUseCase.invoke(orderId).collect {
                _uiStateUpdate.value = uiState
            }
        }
    }

    fun changeStateUpdateToIdle() {
        _uiStateUpdate.value = UiState.Idle
    }
    //`feat: bổ sung xử lý cập nhật, hủy đơn hàng và reset trạng thái trong OrderViewModel`

}