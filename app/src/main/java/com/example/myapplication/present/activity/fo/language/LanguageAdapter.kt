package com.example.myapplication.present.activity.fo.language

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseRecyclerViewAdapter
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.databinding.ItemLanguageBinding
import com.example.myapplication.domain.model.domain.Language

class LanguageAdapter(
    private val context: Context,
    private val onLanguageClicked: (index: Int, language: Language) -> Unit
): BaseRecyclerViewAdapter<Language>() {
    var indexSelect: Int = 0
        set(value) {
            val indexSelectedBefore = field
            field = value
            notifyItemChanged(value)
            notifyItemChanged(indexSelectedBefore)
        }

    override fun getItemLayout(): Int = R.layout.item_language

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<Language>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }
//    `feat: thêm LanguageAdapter hiển thị danh sách ngôn ngữ`

}