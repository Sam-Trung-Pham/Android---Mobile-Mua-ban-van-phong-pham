package com.example.myapplication.domain.usecase.comment

import com.example.myapplication.domain.repository.CommentCacheRepository
import javax.inject.Inject

class GetAllCommentByIdUseCase @Inject constructor(
    private val commentRepository: CommentCacheRepository
) {
    operator fun invoke(id: String) = commentRepository.getAllCommentBy(id)

}
//`feat: thêm GetAllCommentByIdUseCase lấy bình luận theo id`