package com.example.myapplication.present.activity.prod

import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.domain.model.dto.res.ResVariantDTO
import com.example.myapplication.domain.model.entity.FavoriteEntity
import com.example.myapplication.domain.usecase.cart.InsertCartUseCase
import com.example.myapplication.domain.usecase.comment.GetCommentUseCase
import com.example.myapplication.domain.usecase.favorite.DeleteFavoriteUseCase
import com.example.myapplication.domain.usecase.favorite.GetFavoriteUseCase
import com.example.myapplication.domain.usecase.favorite.InsertFavoriteUseCase
import com.example.myapplication.domain.usecase.feedback.GetListCacheFeedbackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val insertCartUseCase: InsertCartUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,

    getAllCommentUseCase: GetCommentUseCase,
    getAllFeedbackUseCase: GetListCacheFeedbackUseCase,
) : BaseViewModel() {
    private val _favoriteEntity = MutableStateFlow<FavoriteEntity?>(null)
    val favoriteEntity = _favoriteEntity.asStateFlow()

    val stateGetAllComment = getAllFeedbackUseCase.invoke()

    init {
        launchIO {
            getAllCommentUseCase.invoke().collect { }
        }
    }
    //`feat: thêm ProductViewModel quản lý giỏ hàng, yêu thích và dữ liệu đánh giá sản phẩm`
    fun addProductToCart(
        productId: String,
        variant: ResVariantDTO,
        price: Double,
    ) = viewModelScope.launch {
        insertCartUseCase.invoke(
            productId,
            variant,
            price
        ).collect {

        }
    }

    fun searchProductInFavorite(id: String) = viewModelScope.launch {
        _favoriteEntity.emit(getFavoriteUseCase.invoke(id).first())
    }

    fun removeFavoriteByIdProduct(id: String) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(id)
        _favoriteEntity.emit(null)
    }

    fun addFavoriteByIdProduct(id: String) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(FavoriteEntity(productId = id))
        _favoriteEntity.emit(
            FavoriteEntity(
                id = 0,
                productId = id
            )
        )
    }
    //`feat: bổ sung xử lý thêm giỏ hàng và quản lý yêu thích trong ProductViewModel`
}