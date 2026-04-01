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

}