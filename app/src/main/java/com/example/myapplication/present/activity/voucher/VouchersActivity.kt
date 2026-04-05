package com.example.myapplication.present.activity.voucher

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.getRemainingTime
import com.example.myapplication.common.base.ext.goneView
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.common.base.ext.visibleView
import com.example.myapplication.databinding.ActivityVouchersBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VouchersActivity : BaseActivity<ActivityVouchersBinding>() {

    private val viewModel: VoucherViewModel by viewModels()

    private var voucherAdapter: VoucherAdapter? = null

    override fun getLayoutActivity(): Int = R.layout.activity_vouchers

    override fun initViews() {
        super.initViews()

        binding.rcvVoucher.apply {
            voucherAdapter = VoucherAdapter(
                onSelected = { index, voucher ->
                    voucherAdapter?.indexSelect = index

                    binding.tvCountVoucher.visibleView()
                    binding.tvMsgVoucherApplied.visibleView()
                }
            )

            adapter = voucherAdapter
        }

        binding.tvCountVoucher.goneView()
        binding.tvMsgVoucherApplied.goneView()
    }
    //`feat: thêm VouchersActivity hiển thị danh sách mã giảm giá`
    override fun onClickViews() {
        super.onClickViews()

        binding.icBack.click {
            finish()
        }

        binding.btnApplyVoucher.click {
            voucherAdapter?.getVoucherSelect()?.let { voucher ->
                setResult(RESULT_OK, Intent().apply {
                    putExtra(AppConst.KEY_VOUCHER, Gson().toJson(voucher))
                })
                finish()
            } ?: run {

            }
        }
    }
    //`feat: bổ sung xử lý áp dụng mã giảm giá trong VouchersActivity`
    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.state.collect { uiState ->
                when (val response = uiState.uiState) {
                    is UiState.Error -> {
                        showToastOnce(response.message)
                        viewModel.changeStateToIdle()

                        binding.loadingView.goneView()
                        binding.rcvVoucher.goneView()
                    }

                    UiState.Idle -> {

                    }

                    UiState.Loading -> {
                        binding.loadingView.visibleView()
                        binding.rcvVoucher.goneView()
                    }

                    is UiState.Success -> {
                        binding.loadingView.goneView()
                        binding.rcvVoucher.visibleView()

                        val listVouchers = response.data
                        voucherAdapter?.submitData(listVouchers.filter { it.endDate?.getRemainingTime()?.lowercase() != "Đã hết hạn".lowercase() })

                        viewModel.changeStateToIdle()
                    }
                }
            }
        }
    }
    //`feat: bổ sung observer xử lý hiển thị danh sách mã giảm giá trong VouchersActivity`
    
}