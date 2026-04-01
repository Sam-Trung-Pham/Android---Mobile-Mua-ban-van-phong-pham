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
            binding.edtPassword.addTextChangedListener(object : TextWatcher {
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
                ) = viewModel.changePasswordValue(s?.toString() ?: "")
            })
//            `feat: bổ sung lắng nghe thay đổi mật khẩu trong SignUpActivity`
            binding.edtPasswordConfirmation.addTextChangedListener(object : TextWatcher {
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
                ) = viewModel.changeConfirmPasswordValue(s?.toString() ?: "")
            })
//            `feat: bổ sung lắng nghe thay đổi xác nhận mật khẩu trong SignUpActivity`
            binding.edtUsername.addTextChangedListener(object : TextWatcher {
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
                ) = viewModel.changeUsernameValue(s?.toString() ?: "")
            })

            binding.btnSignUp.click {
                onSignUpEvent()
            }
//            `feat: bổ sung lắng nghe thay đổi username và xử lý sự kiện đăng ký trong SignUpActivity`
        }
    }
    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.state.collect { signUpState ->
                when (val uiState = signUpState.uiState) {
                    is UiState.Error -> {
                        showToastOnce(uiState.message)
                        loadingDialog?.cancel()
                        viewModel.changeStateToIdle()
                    }

                    UiState.Idle -> {
                        loadingDialog?.cancel()
                    }

                    UiState.Loading -> {
                        loadingDialog?.show()
                    }

                    is UiState.Success -> {
                        viewModel.changeStateToIdle()
                        loadingDialog?.cancel()

                        if (!isFromProfile) finish()
                        else {
                            startActivity(Intent(thiscom.datn.bia.a.present.activity.auth.su.SignUpActivity, SignInActivity::class.java))
                            finishAffinity()
                        }
                    }
                }
            }
        }
    }
//`feat: bổ sung observer xử lý trạng thái đăng ký trong SignUpActivity`

}