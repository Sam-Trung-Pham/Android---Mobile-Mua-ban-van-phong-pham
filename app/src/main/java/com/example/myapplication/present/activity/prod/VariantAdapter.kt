package com.example.myapplication.present.activity.prod

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseRecyclerViewAdapter
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.setTextColorById
import com.example.myapplication.databinding.ItemVariantBinding
import com.example.myapplication.domain.model.dto.res.ResVariantDTO

class VariantAdapter(
    private val onClick: (index: Int, item: ResVariantDTO) -> Unit
) : BaseRecyclerViewAdapter<ResVariantDTO>() {

    var indexSelect: Int = 0
        set(value) {
            val index = field
            field = value
            notifyItemChanged(index)
            notifyItemChanged(value)
        }

    override fun getItemLayout(): Int = R.layout.item_variant

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<ResVariantDTO>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }
    //`feat: thêm VariantAdapter hiển thị và chọn biến thể sản phẩm`

}