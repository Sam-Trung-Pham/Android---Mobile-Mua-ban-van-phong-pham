package com.example.myapplication.common.base.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager


fun getTopFragment(fragmentActivity: FragmentActivity): Fragment? {
    var fragment: Fragment? = null
    val childCount = fragmentActivity.supportFragmentManager.backStackEntryCount
    if (childCount > 0) {
        val tag = fragmentActivity.supportFragmentManager.getBackStackEntryAt(childCount - 1).name
        fragment = fragmentActivity.supportFragmentManager.findFragmentByTag(tag)
    }
    return fragment
}

fun Fragment.switchFragmentFullSlide(fragmentActivity: FragmentActivity, containerViewId: Int) {
    val transition = fragmentActivity.supportFragmentManager.beginTransaction()
    val tag = this@switchFragmentFullSlide::class.simpleName
    transition.apply {
        add(containerViewId, this@switchFragmentFullSlide, tag)
        addToBackStack(tag)
        commitAllowingStateLoss()
    }
}