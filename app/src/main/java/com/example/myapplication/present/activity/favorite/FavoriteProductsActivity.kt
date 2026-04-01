package com.example.myapplication.present.activity.favorite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.goneView
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.common.base.ext.visibleView
import com.example.myapplication.databinding.ActivityFavoriteProductsBinding
import com.example.myapplication.present.activity.prod.ProductActivity
import com.example.myapplication.present.fragment.home.adapter.ProductAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteProductsActivity : BaseActivity<ActivityFavoriteProductsBinding>() {
    private val viewModel: FavoriteViewModel by viewModels()

    private var productAdapter: ProductAdapter? = null

    override fun getLayoutActivity(): Int = R.layout.activity_favorite_products
    override fun initViews() {
        super.initViews()

        binding.rcvFavorite.apply {
            productAdapter = ProductAdapter(
                contextParams = thiscom.datn.bia.a.present.activity.favorite.FavoriteProductsActivity,
                onProductClick = { index, product ->
                    Gson().toJson(product)?.let { json ->
                        startActivity(
                            Intent(
                                thiscom.datn.bia.a.present.activity.favorite.FavoriteProductsActivity,
                                ProductActivity::class.java
                            ).apply {
                                putExtra(AppConst.KEY_PRODUCT_DETAIL, json)
                            })
                    } ?: run {
                        showToastOnce(getString(R.string.msg_wrong))
                    }
                }
            )

            adapter = productAdapter
        }
    }
    override fun onClickViews() {
        super.onClickViews()

        binding.icChat.click {
            finish()
        }
    }
    //`feat: thêm FavoriteProductsActivity hiển thị danh sách sản phẩm yêu thích`
    @SuppressLint("SetTextI18n")
    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (val response = state.uiState) {
                    is UiState.Error -> {
                        showToastOnce(response.message)

                        binding.loadingView.goneView()
                        binding.rcvFavorite.goneView()

                        viewModel.changeStateToIdle()
                    }

                    UiState.Idle -> {}
                    UiState.Loading -> {
                        binding.loadingView.visibleView()
                        binding.rcvFavorite.goneView()
                        binding.tvCountItemFavorite.goneView()
                    }
//                    `feat: bổ sung observer xử lý trạng thái hiển thị danh sách sản phẩm yêu thích`

}