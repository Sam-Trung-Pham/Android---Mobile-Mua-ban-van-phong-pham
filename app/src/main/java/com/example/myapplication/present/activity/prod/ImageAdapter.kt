package com.example.myapplication.present.activity.prod

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseRecyclerViewAdapter
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.databinding.ItemImageProductBinding

class ImageAdapter(
    private val contextParams: Context,
    private val onItemClicked: (String, Int) -> Unit
): BaseRecyclerViewAdapter<String>() {

    var indexSelect: Int = -1
        set(value) {
            val indexSelected = field
            field = value
            notifyItemChanged(indexSelected)
            notifyItemChanged(field)
        }

    override fun getItemLayout(): Int = R.layout.item_image_product

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<String>) {
        list.apply {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }
    //`feat: thêm ImageAdapter hiển thị danh sách ảnh sản phẩm`

}