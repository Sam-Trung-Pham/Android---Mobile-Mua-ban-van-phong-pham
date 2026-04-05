package com.example.myapplication.present.activity.search

import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.application.GlobalApp
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.goneView
import com.example.myapplication.common.base.ext.visibleView
import com.example.myapplication.common.toListProductDataDTO
import com.example.myapplication.databinding.ActivitySearchBinding
import com.example.myapplication.present.fragment.home.adapter.ProductAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    private var productAdapter: ProductAdapter? = null
    private var gson: Gson? = null

    override fun getLayoutActivity(): Int = R.layout.activity_search

    override fun initViews() {
        super.initViews()

        gson = Gson()
        binding.rcvProduct.apply {
            productAdapter = ProductAdapter(
                contextParams = this@SearchActivity,
                onProductClick = { index, product ->
                    gson?.toJson(product)?.let { json ->
                        GlobalApp.jsonSearchResult = json
                        finish()
                    }
                }
            )

            adapter = productAdapter
        }
    }
    //`feat: thêm SearchActivity cho tìm kiếm sản phẩm`
    }