package com.example.myapplication.domain.usecase.cart

import com.example.myapplication.domain.model.dto.res.ResVariantDTO
import com.example.myapplication.domain.model.entity.CartEntity
import com.example.myapplication.domain.repository.CartRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(
        id: String,
        variant: ResVariantDTO,
        price: Double,
    ) = flow {
        val listSearch = cartRepository.searchCartByIdProd(id)

        val response =
            listSearch.firstOrNull { it.idProd == id && it.variant.color == variant.color }
        //`feat: thêm InsertCartUseCase xử lý thêm sản phẩm vào giỏ hàng`

    }
}