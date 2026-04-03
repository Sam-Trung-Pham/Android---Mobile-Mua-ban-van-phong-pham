package com.example.myapplication.present.activity.order.history

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.databinding.ActivityOrderBinding
import com.example.myapplication.domain.model.domain.OrderState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderActivity : BaseActivity<ActivityOrderBinding>() {
    private val viewModel: OrderViewModel by viewModels()

    private var orderAdapter: OrderAdapter? = null
    private var orderStateAdapter: OrderStateAdapter? = null

    private val onPageChangeCbListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            binding.rcvStatusOrder.scrollToPosition(position)
            orderStateAdapter?.indexSelect = position
        }
    }
    override fun getLayoutActivity(): Int = R.layout.activity_order

    override fun initViews() {
        super.initViews()

        binding.vpgTab.apply {
            orderAdapter = OrderAdapter(thiscom.datn.bia.a.present.activity.order.history.OrderActivity)
            adapter = orderAdapter
            registerOnPageChangeCallback(onPageChangeCbListener)
        }

        binding.rcvStatusOrder.apply {
            orderStateAdapter = OrderStateAdapter(
                contextParams = thiscom.datn.bia.a.present.activity.order.history.OrderActivity,
                onStatusClick = { index, status ->
                    orderStateAdapter?.indexSelect = index
                    binding.vpgTab.currentItem = index
                }
            ).apply {
                submitData(OrderState.getListStatus())
            }

            adapter = orderStateAdapter
        }

        binding.vpgTab.currentItem =
            intent.getIntExtra(AppConst.KEY_ORDER_TYPE, 0)
    }
    //`feat: thêm OrderActivity hiển thị lịch sử đơn hàng theo trạng thái`
    override fun onClickViews() {
        super.onClickViews()

        binding.icBack.click { finish() }
    }

    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.state.collect { orderState ->
                when (val response = orderState.uiState) {
                    is UiState.Error -> {
                        showToastOnce(response.message)

                        viewModel.changeStateToIdle()
                    }

                    UiState.Idle -> Unit

                    UiState.Loading -> Unit

                    is UiState.Success -> {
                        viewModel.changeStateToIdle()
                    }
                }
            }
        }
    }
    //`feat: bổ sung xử lý sự kiện và quan sát trạng thái đơn hàng trong OrderActivity`

}