package com.example.myapplication.domain.usecase.favorite

import com.example.myapplication.domain.model.entity.FavoriteEntity
import com.example.myapplication.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(e: FavoriteEntity) = favoriteRepository.insertFavorite(e)
}
//`feat: thêm InsertFavoriteUseCase thêm sản phẩm vào yêu thích`