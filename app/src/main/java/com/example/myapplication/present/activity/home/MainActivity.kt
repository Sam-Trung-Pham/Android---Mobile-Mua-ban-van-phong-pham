package com.example.myapplication.present.activity.home

import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.selectedTab
import com.example.myapplication.common.base.ext.unSelectedTab
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication..domain.model.dto.res.ResLoginUserDTO
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>() {
    private var vpgAdapter: VpgAdapter? = null

    private val onPageChangeCallBack = object: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            when (position) {
                0 -> {
                    binding.tvTabHome.selectedTab(thiscom.datn.bia.a.present.activity.home.MainActivity)
                    binding.tvTabCart.unSelectedTab(thiscom.datn.bia.a.present.activity.home.MainActivity)
                    binding.tvTabProfile.unSelectedTab(thiscom.datn.bia.a.present.activity.home.MainActivity)

                    binding.icHome.isActivated = true
                    binding.icCart.isActivated = false
                    binding.icProfile.isActivated = false
                }

                1 -> {
                    binding.tvTabHome.unSelectedTab(thiscom.datn.bia.a.present.activity.home.MainActivity)
                    binding.tvTabCart.selectedTab(thiscom.datn.bia.a.present.activity.home.MainActivity)
                    binding.tvTabProfile.unSelectedTab(thiscom.datn.bia.a.present.activity.home.MainActivity)

                    binding.icHome.isActivated = false
                    binding.icCart.isActivated = true
                    binding.icProfile.isActivated = false
                }

                2 -> {
                    binding.tvTabHome.unSelectedTab(thiscom.datn.bia.a.present.activity.home.MainActivity)
                    binding.tvTabCart.unSelectedTab(thiscom.datn.bia.a.present.activity.home.MainActivity)
                    binding.tvTabProfile.selectedTab(thiscom.datn.bia.a.present.activity.home.MainActivity)

                    binding.icHome.isActivated = false
                    binding.icCart.isActivated = false
                    binding.icProfile.isActivated = true
                }
            }
        }
    }
//    `feat: thêm MainActivity và xử lý điều hướng tab trang chủ`
override fun getLayoutActivity(): Int = R.layout.activity_main

    override fun initViews() {
        super.initViews()

        if (SharedPrefCommon.isFirstInstall) SharedPrefCommon.isFirstInstall = false
        initVpg()

        Log.d("sampt", "${
            Gson().fromJson(SharedPrefCommon.jsonAcc, ResLoginUserDTO::class.java)
        }")

        Log.d("sampt", "${
            Gson().fromJson(SharedPrefCommon.jsonAcc, ResLoginUserDTO::class.java)?.user?.id
        }")
    }
//    `feat: bổ sung khởi tạo MainActivity và xử lý trạng thái cài đặt lần đầu`

}