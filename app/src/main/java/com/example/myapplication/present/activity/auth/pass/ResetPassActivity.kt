package com.example.myapplication.present.activity.auth.pass

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.databinding.ActivityResetPassBinding
import com.example.myapplication.domain.model.dto.req.ReqResetPass
import com.example.myapplication.present.dialog.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResetPassActivity : BaseActivity<ActivityResetPassBinding>() {

    private var loadingDialog: LoadingDialog? = null

    private val viewModel: ResetPassViewModel by viewModels()

    override fun getLayoutActivity(): Int = R.layout.activity_reset_pass

    override fun initViews() {
        super.initViews()

        loadingDialog = LoadingDialog(this)
    }
    //`feat: thêm ResetPassActivity cho màn hình đặt lại mật khẩu`
    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UiState.Error -> {
                        loadingDialog?.dismiss()
                        showToastOnce(uiState.message)
                    }
                    UiState.Idle -> {}
                    UiState.Loading -> {
                        loadingDialog?.show()
                    }
                    is UiState.Success -> {
                        loadingDialog?.dismiss()
                        finish()
                        showToastOnce(uiState.data.message?.ifEmpty { "Đổi mật khẩu thành công" } ?: "Đổi mật khẩu thành công")
                    }
                }
            }
        }
    }
    //`feat: bổ sung observer xử lý trạng thái đặt lại mật khẩu trong ResetPassActivity`

}