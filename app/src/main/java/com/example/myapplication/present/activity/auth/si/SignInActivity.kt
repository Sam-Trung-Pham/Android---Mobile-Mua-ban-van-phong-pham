package com.example.myapplication.present.activity.auth.si

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.databinding.ActivitySignInBinding
import com.example.myapplication.present.activity.auth.pass.ForgotPassActivity
import com.example.myapplication.present.activity.auth.su.SignUpActivity
import com.example.myapplication.present.activity.home.MainActivity
import com.example.myapplication.present.dialog.LoadingDialog
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>() {

    private val viewModel: SignInViewModel by viewModels()

    private var loadingDialog: LoadingDialog? = null

    override fun getLayoutActivity(): Int = R.layout.activity_sign_in

    override fun initViews() {
        super.initViews()

        loadingDialog = LoadingDialog(this)
    }
    //`feat: thêm SignInActivity cho màn hình đăng nhập`
    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.state.collect { signInState ->
                when (val state = signInState.uiState) {
                    is UiState.Error -> {
                        loadingDialog?.dismiss()
                        showToastOnce(state.message)
                        viewModel.changeStateToIdle()
                    }

                    UiState.Idle -> loadingDialog?.dismiss()
                    UiState.Loading -> loadingDialog?.show()
                    is UiState.Success -> {
                        loadingDialog?.dismiss()
                        val json = Gson().toJson(state.data)
                        SharedPrefCommon.jsonAcc = json ?: ""

                        startActivity(Intent(thiscom.datn.bia.a.present.activity.auth.si.SignInActivity, MainActivity::class.java))
                        finishAffinity()

                        viewModel.changeStateToIdle()
                    }
                }
            }
        }
    }
    //`feat: bổ sung observer xử lý trạng thái đăng nhập trong SignInActivity`
    override fun onDestroy() {
        loadingDialog?.dismiss()
        loadingDialog = null

        super.onDestroy()
    }

    private fun onSignInEvent() {
        if (viewModel.state.value.email.isEmpty() || viewModel.state.value.password.isEmpty()) {
            showToastOnce(getString(R.string.msg_input_null))
            return
        }

        viewModel.onSignInEvent()
    }
    //`feat: bổ sung xử lý sự kiện đăng nhập và giải phóng LoadingDialog trong SignInActivity`
}