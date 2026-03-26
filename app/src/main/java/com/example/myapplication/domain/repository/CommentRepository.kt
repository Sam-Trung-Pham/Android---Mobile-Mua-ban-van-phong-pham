package com.example.myapplication.domain.repository

import com.datn.bia.a.data.network.factory.ResultWrapper
import com.example.myapplication.domain.model.dto.req.ReqCommentDTO
import com.example.myapplication.domain.model.dto.res.ResCommentDTO

interface CommentRepository {
    suspend fun createComment(req: ReqCommentDTO): ResultWrapper<Any>
    suspend fun getComment(): ResultWrapper<List<ResCommentDTO>>
}
//feat: thêm interface CommentCacheRepository cho lưu bình luận cục bộ