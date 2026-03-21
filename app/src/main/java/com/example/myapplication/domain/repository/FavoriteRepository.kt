package com.example.myapplication.domain.repository
import com.example.myapplication.domain.model.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun insertFavorite(entity: FavoriteEntity)
    suspend fun deleteFavoriteByProductId(id: String)
    suspend fun searchFavoriteByProductId(id: String): FavoriteEntity?
    fun getAllFavorite(): Flow<List<FavoriteEntity>>
}
// `feat: thêm interface FavoriteRepository cho quản lý sản phẩm yêu thích`