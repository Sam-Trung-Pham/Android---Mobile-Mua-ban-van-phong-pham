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
private val onboardingCallback = object : ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)

        onPageSelectedEvent(position)
    }
}

        override fun getLayoutActivity(): Int = R.layout.activity_onboarding

        override fun initViews() {
            super.initViews()

            binding.vgp2.apply {
                onbAdapter = OnboardingAdapter(thiscom.datn.bia.a.present.activity.fo.onboarding.OnboardingActivity).apply {
                    submitData(Onboarding.getAllOnboardings())
                }
                adapter = onbAdapter
                clipToPadding = false
                clipChildren = false
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
                (getChildAt(0) as? RecyclerView)?.addOnItemTouchListener(lastPageSwipeListener)
                registerOnPageChangeCallback(onboardingCallback)
            }
        }
//        `feat: bổ sung khởi tạo ViewPager2 và xử lý chuyển trang onboarding`
override fun onClickViews() {
    super.onClickViews()

    binding.tvNext.click {
        onNextEvent()
    }
}

        private fun onPageSelectedEvent(position: Int) =
            when (position) {
                0 -> onPage1Selected()
                1 -> onPage2Selected()
                2 -> onPage3Selected()
                else -> Unit
            }

        private fun onNextEvent() {
            val isLastItem =
                binding.vgp2.currentItem == onbAdapter.list.size - 1
            if (isLastItem) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else binding.vgp2.currentItem++
        }
//        `feat: bổ sung xử lý chuyển trang và hoàn tất onboarding`
