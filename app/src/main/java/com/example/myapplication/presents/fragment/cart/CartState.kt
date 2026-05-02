package com.example.myapplication.presents.fragment.cart

import com.datn.domain.model.entity.CartEntity
import com.domain.model.entity.ProductEntity

data class CartState(
    val listProductEntity: List<ProductEntity> = emptyList(),
    val listCarts: List<CartEntity> = emptyList(),
) {
}