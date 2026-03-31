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

}