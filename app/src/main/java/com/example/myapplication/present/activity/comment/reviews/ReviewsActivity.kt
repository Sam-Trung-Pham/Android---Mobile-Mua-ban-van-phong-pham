package com.example.myapplication.present.activity.comment.reviews

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.goneView
import com.example.myapplication.common.base.ext.visibleView
import com.example.myapplication.common.toListResCommentDTO
import com.example.myapplication.databinding.ActivityReviewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewsActivity : BaseActivity<ActivityReviewsBinding>() {

    private val viewModel: ReviewViewModel by viewModels()

    private var reviewAdapter: ReviewAdapter? = null
    private var idProductCurrent: String? = null

    override fun getLayoutActivity(): Int = R.layout.activity_reviews

    override fun initViews() {
        super.initViews()

        idProductCurrent = intent.getStringExtra(AppConst.KEY_ID_PRODUCT)

        binding.rcvComment.apply {
            reviewAdapter = ReviewAdapter()

            adapter = reviewAdapter
        }
    }
//    `feat: thêm ReviewsActivity hiển thị danh sách đánh giá sản phẩm`
override fun onClickViews() {
    super.onClickViews()

    binding.icBack.click {
        finish()
    }
}

    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.state.collect {
                val list = it.listComment.toListResCommentDTO()

                binding.rcvComment.visibleView()
                binding.loadingView.goneView()

                val data = list.filter { comment -> comment.productId?._id == idProductCurrent }
                reviewAdapter?.submitData(data)
                binding.tvStars.text =
                    (data.sumOf { comment -> comment.rating ?: 0 }
                        .toFloat() / if (data.count() == 0) 1 else data.count()).toString().take(3)
            }
        }
    }
//    `feat: bổ sung xử lý hiển thị danh sách và điểm đánh giá trong ReviewsActivity`
}