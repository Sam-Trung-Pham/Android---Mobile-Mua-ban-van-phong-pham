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

}