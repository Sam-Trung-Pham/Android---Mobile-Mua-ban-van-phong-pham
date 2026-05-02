package com.example.myapplication.presents.dialog

import android.content.Context
import com.datn.common.base.BaseDialog
import com.datn.common.base.ext.click
import com.datn.databinding.DialogMessageBinding

class MessageDialog(
    contextParam: Context,
    private val onSend: (String) -> Unit,
    private val onClose: () -> Unit
) : BaseDialog<DialogMessageBinding>(
    context = contextParam,
    isSetShowBottom = true
) {
    override fun inflateBinding(): DialogMessageBinding =
        DialogMessageBinding.inflate(layoutInflater)

    override fun onClickViews() {
        super.onClickViews()

        binding.icClose.click {
            onClose.invoke()
            dismiss()
            binding.edtMessage.text.clear()
        }

        binding.btnSend.click {
            onSend.invoke(binding.edtMessage.text.toString().trim())
            dismiss()
        }
    }
}