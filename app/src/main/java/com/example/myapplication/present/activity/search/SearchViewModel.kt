package com.example.myapplication.present.activity.search

import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.domain.model.dto.res.ResProductDataDTO
import com.example.myapplication.domain.usecase.prod_cache.GetListProductCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getListProductCacheUseCase: GetListProductCacheUseCase
) : BaseViewModel() {
    val stateProduct = getListProductCacheUseCase.invoke()

    private val _listProduct = MutableStateFlow<List<ResProductDataDTO>>(emptyList())
    val listProduct = _listProduct.asStateFlow()

    fun cacheListProduct(list: List<ResProductDataDTO>) {
        _listProduct.value = list
    }

    private val _keySearch = MutableStateFlow("")
    val keySearch = _keySearch.asStateFlow()

    fun changeKeySearch(key: String?) {
        _keySearch.value = key ?: ""
    }
}
//`feat: thêm SearchViewModel quản lý dữ liệu và từ khóa tìm kiếm sản phẩm`