package com.example.myapplication.present.activity.prod

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.formatVND
import com.example.myapplication.common.base.ext.goneView
import com.example.myapplication.common.base.ext.isNetwork
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.common.base.ext.visibleView
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.databinding.ActivityProductBinding
import com.example.myapplication.domain.model.dto.res.ResProductDataDTO
import com.example.myapplication.domain.model.dto.res.ResVariantDTO
import com.example.myapplication.present.activity.auth.si.SignInActivity
import com.example.myapplication.present.activity.comment.reviews.ReviewsActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductActivity : BaseActivity<ActivityProductBinding>() {

    private val viewModel: ProductViewModel by viewModels()

    private var gson: Gson? = null
    private var imageAdapter: ImageAdapter? = null
    private var idProdCur: String? = null
    private var variant: ResVariantDTO? = null
    private var price: Double = 0.0
    private var variantAdapter: VariantAdapter? = null

    override fun getLayoutActivity(): Int = R.layout.activity_product

    override fun initViews() {
        super.initViews()

        gson = Gson()
        receiveData()
    }

    @SuppressLint("SetTextI18n")
    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.favoriteEntity.collect { favorite ->
                binding.icFavorite.isActivated =
                    favorite != null
            }
        }

        lifecycleScope.launch {
            viewModel.stateGetAllComment.collect { listComment ->
                val data = listComment.filter { it.idProduct == idProdCur }
                val star = (data.sumOf { it.rating }
                    .toFloat() / if (data.count() == 0) 1 else data.count())
                binding.tvStars.text = star.toString().take(3)
            }
        }
        binding.tvBought.text = "${getString(R.string.sold)} ${(0..100).random()}"
    }
    ////`feat: thêm ProductActivity hiển thị chi tiết sản phẩm và xử lý trạng thái yêu thích, đánh giá`

    override fun onClickViews() {
        super.onClickViews()

        binding.icBack.click { finish() }

        binding.btnAddCart.click { onAddCartEvent() }

        binding.btnBuyNow.click { onEventBuyNow() }

        binding.icFavorite.click { onFavoriteEvent() }

        binding.btnSeeAllComment.click {
            startActivity(Intent(this, ReviewsActivity::class.java).apply {
                putExtra(AppConst.KEY_ID_PRODUCT, idProdCur)
            })
        }
    }

    override fun onDestroy() {
        gson = null
        imageAdapter?.list?.clear()
        imageAdapter = null
        variantAdapter?.list?.clear()
        variantAdapter = null

        super.onDestroy()
    }
    //`feat: bổ sung xử lý sự kiện và giải phóng tài nguyên trong ProductActivity`


}