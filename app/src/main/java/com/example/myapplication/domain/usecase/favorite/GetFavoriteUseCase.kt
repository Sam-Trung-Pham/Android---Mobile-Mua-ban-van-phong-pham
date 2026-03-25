package com.example.myapplication.domain.usecase.favorite

import com.example.myapplication.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(id: String) = flow {
        emit(favoriteRepository.searchFavoriteByProductId(id))
    }
}
//`feat: thêm GetFavoriteUseCase kiểm tra sản phẩm yêu thích theo id`