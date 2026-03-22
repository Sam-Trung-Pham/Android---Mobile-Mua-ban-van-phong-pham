package com.example.myapplication.domain.usecase.comment

import com.example.myapplication.domain.model.entity.CommentEntity
import com.example.myapplication.domain.repository.CommentCacheRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CacheCommentUseCase @Inject constructor(
    private val commentCacheRepository: CommentCacheRepository
) {
    operator fun invoke(entity: CommentEntity) = flow {
        emit(commentCacheRepository.insertComment(entity))
    }
}
//`feat: thêm CacheCommentUseCase lưu bình luận vào cache`