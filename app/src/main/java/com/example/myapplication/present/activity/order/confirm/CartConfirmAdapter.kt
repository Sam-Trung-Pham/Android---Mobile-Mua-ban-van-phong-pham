package com.example.myapplication.present.activity.order.confirm

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseRecyclerViewAdapter
import com.example.myapplication.common.base.ext.formatVND
import com.example.myapplication.common.base.ext.goneView
import com.example.myapplication.common.base.ext.visibleView
import com.example.myapplication.databinding.ItemProdOrderBinding
import com.example.myapplication.domain.model.domain.Cart
import kotlin.compareTo
import kotlin.div
import kotlin.times

class CartConfirmAdapter(
    private val contextParams: Context
) : BaseRecyclerViewAdapter<Cart>() {
    override fun getItemLayout(): Int = R.layout.item_prod_order

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<Cart>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }
//    `feat: thêm CartConfirmAdapter hiển thị sản phẩm trong màn xác nhận đơn hàng`

}