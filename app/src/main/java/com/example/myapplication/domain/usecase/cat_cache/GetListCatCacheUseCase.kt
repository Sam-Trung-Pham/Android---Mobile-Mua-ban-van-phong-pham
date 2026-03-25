package com.example.myapplication.domain.usecase.cat_cache

import com.example.myapplication.domain.repository.CategoryCacheRepository
import javax.inject.Inject

class GetListCatCacheUseCase @Inject constructor(
    private val categoryCacheRepository: CategoryCacheRepository
) {
    operator fun invoke() = categoryCacheRepository.getListCategory()
}
//`feat: thêm GetListCatCacheUseCase lấy danh sách danh mục từ cache`