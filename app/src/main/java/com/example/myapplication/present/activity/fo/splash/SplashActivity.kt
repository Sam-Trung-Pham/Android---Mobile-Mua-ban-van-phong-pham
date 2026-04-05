package com.example.myapplication.present.activity.fo.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.myapplication.R
import com.example.myapplication.application.GlobalApp
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.databinding.ActivitySplashBinding
import com.example.myapplication.present.activity.fo.language.LanguageActivity
import com.example.myapplication.present.activity.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun getLayoutActivity(): Int = R.layout.activity_splash

    override fun initViews() {
        super.initViews()

        GlobalApp.jsonSearchResult = ""

        Handler(Looper.getMainLooper()).postDelayed({
            if (SharedPrefCommon.isFirstInstall) {
                startActivity(Intent(this, LanguageActivity::class.java).apply {
                    putExtra(AppConst.KEY_FROM_SPLASH, true)
                })
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, 5000)
    }
}
//`feat: thêm SplashActivity xử lý điều hướng khởi động ứng dụng`