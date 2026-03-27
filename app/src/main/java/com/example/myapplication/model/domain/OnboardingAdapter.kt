package com.example.myapplication.model.domain

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseRecyclerViewAdapter
import com.example.myapplication.databinding.ItemOnboardingBinding
class OnboardingAdapter(
    private val context: Context
): BaseRecyclerViewAdapter<Onboarding>() {
    override fun getItemLayout(): Int = R.layout.item_onboarding

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<Onboarding>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun setData(
        binding: ViewDataBinding,
        item: Onboarding,
        layoutPosition: Int
    ) {
        if (binding is ItemOnboardingBinding) {
            Glide.with(context).load(item.imageRes).into(binding.imgOnb)
            binding.tvTitle.text = context.getString(item.titleRes)
            binding.tvDes.text = context.getString(item.desRes)
        }
    }
}
//`feat: thêm OnboardingAdapter hiển thị dữ liệu onboarding`