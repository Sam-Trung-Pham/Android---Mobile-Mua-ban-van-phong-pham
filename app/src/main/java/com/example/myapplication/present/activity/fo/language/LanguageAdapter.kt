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
override fun setData(
    binding: ViewDataBinding,
    item: Language,
    layoutPosition: Int
) {
    if (binding is ItemLanguageBinding) {
        Glide.with(context).load(item.image).into(binding.icCountriesFlag)
        binding.tvLanguageName.text = item.languageName
        binding.icRd.isActivated = layoutPosition == indexSelect
    }
}
//    `feat: bổ sung bind dữ liệu và trạng thái chọn trong LanguageAdapter`

}