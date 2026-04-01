package com.example.myapplication.present.activity.comment.reviews

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseRecyclerViewAdapter
import com.example.myapplication.databinding.ItemReviewBinding
import com.example.myapplication.domain.model.dto.res.ResCommentDTO
import kotlin.toString

class ReviewAdapter: BaseRecyclerViewAdapter<ResCommentDTO>() {
    override fun getItemLayout(): Int = R.layout.item_review

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<ResCommentDTO>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun setData(
        binding: ViewDataBinding,
        item: ResCommentDTO,
        layoutPosition: Int
    ) {
        if (binding is ItemReviewBinding) {
            binding.tvIdUser.text = item.userId?.email
            binding.tvStars.text = item.rating?.toString() ?: 0.toString()
            binding.tvComment.text = item.content
        }
    }
}
//`feat: thêm ReviewAdapter hiển thị danh sách đánh giá sản phẩm`