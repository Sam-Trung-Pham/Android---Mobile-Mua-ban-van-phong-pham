package com.example.myapplication.domain.repository
import com.example.myapplication.domain.model.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

interface CommentCacheRepository {
    suspend fun insertComment(commentEntity: CommentEntity)
    fun getAllCommentBy(id: String): Flow<List<CommentEntity>>
}

//`feat: thêm interface CommentCacheRepository cho lưu bình luận cục bộ`