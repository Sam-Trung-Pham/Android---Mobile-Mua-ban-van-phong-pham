package com.example.myapplication.domain.usecase.favorite

import com.example.myapplication.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(id: String) = favoriteRepository.deleteFavoriteByProductId(id)
}
//`feat: thêm DeleteFavoriteUseCase xóa sản phẩm yêu thích`