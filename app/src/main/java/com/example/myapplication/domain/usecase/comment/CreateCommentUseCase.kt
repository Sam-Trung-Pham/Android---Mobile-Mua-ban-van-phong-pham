package com.example.myapplication.domain.usecase.comment
import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.data.network.factory.ResultWrapper
import com.example.myapplication.domain.model.dto.req.ReqCommentDTO
import com.example.myapplication.domain.repository.CommentRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
class CreateCommentUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val commentRepository: CommentRepository
) {
    operator fun invoke(req: ReqCommentDTO) = flow {
        emit(UiState.Loading)
        //`feat: thêm CreateCommentUseCase xử lý tạo bình luận sản phẩm`

    }
}