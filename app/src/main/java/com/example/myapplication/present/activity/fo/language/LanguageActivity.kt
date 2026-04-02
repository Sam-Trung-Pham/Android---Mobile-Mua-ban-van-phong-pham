package com.example.myapplication.present.activity.fo.language

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.databinding.ActivityLanguageBinding
import com.example.myapplication.domain.model.domain.Language
import com.example.myapplication.present.activity.fo.splash.SplashActivity
import com.example.myapplication.present.activity.home.MainActivity
import com.example.myapplication.present.dialog.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageActivity : BaseActivity<ActivityLanguageBinding>() {
    private var languageAdapter: LanguageAdapter? = null
    private var loadingDialog: LoadingDialog? = null

    private var isFromSplash: Boolean = false

    override fun getLayoutActivity(): Int = R.layout.activity_language

    override fun initViews() {
        super.initViews()

        isFromSplash = intent.getBooleanExtra(AppConst.KEY_FROM_SPLASH, false)
        loadingDialog = LoadingDialog(this)
        initRcvLanguage()
    }
    override fun onClickViews() {
        super.onClickViews()

        binding.icTick.click {
            val isoLanguageCurrent = languageAdapter?.getIsoLanguageCurrent() ?: "en"
            SharedPrefCommon.languageCode = isoLanguageCurrent

            if (isFromSplash) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                loadingDialog?.show()

                Handler(Looper.getMainLooper()).postDelayed({
                    loadingDialog?.dismiss()
                    startActivity(Intent(this, SplashActivity::class.java))
                    finishAffinity()
                }, 3_000)
            }
        }
    }
//    `feat: thêm LanguageActivity cho chọn ngôn ngữ ứng dụng`

}