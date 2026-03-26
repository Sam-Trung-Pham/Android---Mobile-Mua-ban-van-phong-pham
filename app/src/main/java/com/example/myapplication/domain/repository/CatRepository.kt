package com.example.myapplication.domain.repository
import com.example.myapplication.domain.model.dto.res.ResCatDTO

interface CatRepository {
    suspend fun fetchAllCat(): ResultWrapper<List<ResCatDTO>>
}

//`feat: thêm interface CatRepository lấy danh sách danh mục`