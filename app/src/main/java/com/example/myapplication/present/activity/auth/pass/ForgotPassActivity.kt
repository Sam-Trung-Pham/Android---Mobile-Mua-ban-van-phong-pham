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

}}