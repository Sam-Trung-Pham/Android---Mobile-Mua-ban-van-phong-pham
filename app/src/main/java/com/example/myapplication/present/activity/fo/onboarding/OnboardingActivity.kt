package com.example.myapplication.present.activity.fo.onboarding

import android.content.Intent
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.databinding.ActivityOnboardingBinding
import com.example.myapplication.domain.model.domain.Onboarding
import com.example.myapplication.domain.model.domain.OnboardingAdapter
import com.example.myapplication.present.activity.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity: BaseActivity<ActivityOnboardingBinding>() {
    private lateinit var onbAdapter: OnboardingAdapter

    private val lastPageSwipeListener = object : RecyclerView.OnItemTouchListener {
        private var initialX = 0f

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            when (e.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = e.x
                }

                MotionEvent.ACTION_MOVE -> {
                    val deltaX = e.x - initialX

                    if (deltaX < -50 && !rv.canScrollHorizontally(1)) {
                        onNextEvent()
                        return true // Consume event
                    }
                }
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) = Unit
        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) = Unit
//        `feat: thêm OnboardingActivity cho màn hình giới thiệu ứng dụng`
