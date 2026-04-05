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
    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.stateProduct.collect { list ->
                binding.rcvProduct.visibleView()
                binding.loadingView.goneView()

                val listData = list.toListProductDataDTO()
                viewModel.cacheListProduct(listData)
                productAdapter?.submitData(listData)
            }
        }

        lifecycleScope.launch {
            viewModel.keySearch.collect { key ->
                if (key.isEmpty()) productAdapter?.submitData(viewModel.listProduct.first())
                else {
                    val listTemp = viewModel.listProduct.first()
                    val resultList =
                        listTemp.filter { it.name?.lowercase()?.contains(key.lowercase()) == true }

                    productAdapter?.submitData(resultList)
                }
            }
        }
    }
    //`feat: bổ sung xử lý hiển thị và tìm kiếm sản phẩm trong SearchActivity`

}