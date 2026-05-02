package com.example.myapplication.present.activity.voucher

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseRecyclerViewAdapter
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.formatVND
import com.example.myapplication.common.base.ext.getRemainingTime
import com.example.myapplication.databinding.ItemVoucherBinding
import com.example.myapplication.domain.model.dto.res.ResVoucherDTO

class VoucherAdapter(
    private val onSelected: (index: Int, voucher: ResVoucherDTO) -> Unit
) : BaseRecyclerViewAdapter<ResVoucherDTO>() {

    var indexSelect = -1
        set(value) {
            val indexSelected = field
            field = value
            notifyItemChanged(indexSelected)
            notifyItemChanged(value)
        }

    override fun getItemLayout(): Int = R.layout.item_voucher

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<ResVoucherDTO>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setData(
        binding: ViewDataBinding,
        item: ResVoucherDTO,
        layoutPosition: Int
    ) {
        if (binding is ItemVoucherBinding) {
            binding.tvCode.text = item.code
            binding.tvDes.text = item.description
            binding.tvExpiring.text = item.endDate?.getRemainingTime() ?: ""
            binding.tvMaxDiscount.text =
                "${binding.root.context.getString(R.string.maximum_discount)}: ${item.maxPriceDis?.formatVND()}"
            binding.tvQuantity.text =
                "${binding.root.context.getString(R.string.quantity)}: ${item.quantity ?: 0}"

            binding.chb.isActivated = (layoutPosition == indexSelect)
        }
    }
    //`feat: thêm VoucherAdapter hiển thị và chọn mã giảm giá`
    override fun onClickViews(binding: ViewDataBinding, obj: ResVoucherDTO, layoutPosition: Int) {
        super.onClickViews(binding, obj, layoutPosition)

        if (binding is ItemVoucherBinding) {
            binding.root.click {
                onSelected.invoke(layoutPosition, obj)
            }
        }
    }

    fun getVoucherSelect(): ResVoucherDTO? =
        if (indexSelect == -1) null else list[indexSelect]
    //`feat: bổ sung xử lý chọn và lấy mã giảm giá trong VoucherAdapter`
}