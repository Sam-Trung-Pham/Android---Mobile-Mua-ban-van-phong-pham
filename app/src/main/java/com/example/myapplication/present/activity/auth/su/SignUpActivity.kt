package com.example.myapplication.present.activity.auth.su

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.isValidEmailAndroid
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.example.myapplication.present.activity.auth.si.SignInActivity
import com.example.myapplication.present.dialog.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {

    private var isFromProfile: Boolean = false

    private val viewModel: SignUpViewModel by viewModels()

    private var loadingDialog: LoadingDialog? = null

    override fun getLayoutActivity(): Int = R.layout.activity_sign_up

    override fun initViews() {
        super.initViews()

        isFromProfile = intent.getBooleanExtra(AppConst.KEY_FROM_PROFILE, false)
        loadingDialog = LoadingDialog(this)
    }

        override fun onClickViews() {
            super.onClickViews()

            binding.tvSignIn.click {
                if (isFromProfile) {
                    startActivity(Intent(this, SignInActivity::class.java))
                    finishAffinity()
                } else {
                    finish()
                }
            }
            //`feat: thêm SignUpActivity và xử lý điều hướng sang màn hình đăng nhập`
            binding.edtEmail.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) = viewModel.changeEmailValue(s?.toString() ?: "")
            })
//            `feat: bổ sung lắng nghe thay đổi email trong SignUpActivity`

}