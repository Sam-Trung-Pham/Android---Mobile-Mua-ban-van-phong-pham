package com.example.myapplication.present.activity.setting.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseRecyclerViewAdapter
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.databinding.ItemSettingBinding
import com.example.myapplication.domain.model.domain.SettingItem

class SettingItemAdapter(
    private val contextParams: Context,
    private val onSettingItem: (index: Int, setting: SettingItem) -> Unit
): BaseRecyclerViewAdapter<SettingItem>() {
    override fun getItemLayout(): Int = R.layout.item_setting

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<SettingItem>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun setData(
        binding: ViewDataBinding,
        item: SettingItem,
        layoutPosition: Int
    ) {
        if (binding is ItemSettingBinding) {
            binding.tvSetting.text = contextParams.getString(item.titleRes)
        }
    }

    override fun onClickViews(binding: ViewDataBinding, obj: SettingItem, layoutPosition: Int) {
        super.onClickViews(binding, obj, layoutPosition)

        if (binding is ItemSettingBinding) {
            binding.root.click {
                onSettingItem.invoke(layoutPosition, obj)
            }
        }
    }
}
//`feat: thêm SettingItemAdapter hiển thị và xử lý chọn mục cài đặt`