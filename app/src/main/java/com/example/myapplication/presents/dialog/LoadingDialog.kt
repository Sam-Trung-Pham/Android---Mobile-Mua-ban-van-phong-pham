package com.example.myapplication.presents.dialog

import android.content.Context
import com.datn.common.base.BaseDialog
import com.datn.databinding.DialogLoadingBinding

class LoadingDialog(
    context: Context
) : BaseDialog<DialogLoadingBinding>(context) {
    override fun inflateBinding(): DialogLoadingBinding =
        DialogLoadingBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()

        setCancelable(false)
    }
}