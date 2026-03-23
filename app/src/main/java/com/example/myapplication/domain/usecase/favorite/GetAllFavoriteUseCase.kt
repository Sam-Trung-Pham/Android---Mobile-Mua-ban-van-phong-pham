package com.example.myapplication.domain.usecase.favorite
import com.example.myapplication.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke() = favoriteRepository.getAllFavorite()
}
//`feat: thêm GetAllFavoriteUseCase lấy danh sách sản phẩm yêu thích`