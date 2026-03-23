package com.example.myapplication.domain.usecase.cart
import com.example.myapplication.domain.repository.CartRepository
import javax.inject.Inject

class GetAllCartsUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke() = cartRepository.getAllCartEnable()
}
//`feat: thêm GetAllCartsUseCase lấy danh sách giỏ hàng`