package com.example.myapplication.present.activity.auth.pass

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.databinding.ActivityForgotpassBinding
import com.example.myapplication.domain.model.dto.req.ReqForgotPass
import com.example.myapplication.present.activity.auth.pass.ResetPassActivity
import com.example.myapplication.present.dialog.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPassActivity : BaseActivity<ActivityForgotpassBinding>() {

    private val viewModel: ForgotViewModel by viewModels()

    private var loadingDialog: LoadingDialog? = null

    override fun getLayoutActivity(): Int = R.layout.activity_forgotpass

    override fun initViews() {
        super.initViews()

        loadingDialog = LoadingDialog(this)
    }
    //`feat: thêm ForgotPassActivity cho màn hình quên mật khẩu`
    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UiState.Error -> {
                        loadingDialog?.dismiss()
                        showToastOnce(uiState.message.ifEmpty { getString(R.string.msg_email_not_exists) })
                    }

                    UiState.Idle -> {}
                    UiState.Loading -> {
                        loadingDialog?.show()
                    }

                    is UiState.Success -> {
                        loadingDialog?.show()
                        startActivity(
                            Intent(
                                thiscom.datn.bia.a.present.activity.auth.pass.ForgotPassActivity,
                                ResetPassActivity::class.java
                            )
                        )
                        finish()
                    }
                }
            }
        }
    }
    //`feat: bổ sung observer xử lý trạng thái quên mật khẩu trong ForgotPassActivity`

}}