package com.example.myapplication.present.activity.order.confirm

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.MethodPayment
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.formatVND
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.common.payment.Api.CreateOrder
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.databinding.ActivityConfirmOrderBinding
import com.example.myapplication.domain.model.domain.Cart
import com.example.myapplication.domain.model.dto.req.ReqProdCheckOut
import com.example.myapplication.domain.model.dto.res.ResLoginUserDTO
import com.example.myapplication.present.activity.order.history.OrderActivity
import com.example.myapplication.present.dialog.LoadingDialog
import com.example.myapplication.present.dialog.MessageDialog
import com.example.myapplication.present.dialog.NotificationDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import vn.zalopay.sdk.Environment
import vn.zalopay.sdk.ZaloPayError
import vn.zalopay.sdk.ZaloPaySDK
import vn.zalopay.sdk.listeners.PayOrderListener

@AndroidEntryPoint
class ConfirmOrderActivity : BaseActivity<ActivityConfirmOrderBinding>() {
    private var gson: Gson? = null
    private var cartConfirmAdapter: CartConfirmAdapter? = null
    private var messageDialog: MessageDialog? = null
    private var loadingDialog: LoadingDialog? = null
    private var notificationDialog: NotificationDialog? = null

    private var paymentMethod = MethodPayment.CASH_ON_DELIVERY.name

    private val viewModel: ConfirmViewModel by viewModels()

    override fun getLayoutActivity(): Int = R.layout.activity_confirm_order
    override fun initViews() {
        super.initViews()

        initZaloPay()
        gson = Gson()
        paymentMethod = intent.getStringExtra(AppConst.KEY_PAYMENT_METHOD) ?: paymentMethod
        cartConfirmAdapter = CartConfirmAdapter(
            contextParams = thiscom.datn.bia.a.present.activity.order.confirm.ConfirmOrderActivity,
        ).apply {
            val listCart = gson?.fromJson<List<Cart>>(
                intent.getStringExtra(AppConst.KEY_LIST_CART),
                object : TypeToken<List<Cart>>() {}.type
            ) ?: emptyList()
            submitData(listCart)
        }
        binding.rcvOrder.adapter = cartConfirmAdapter
        messageDialog = MessageDialog(
            contextParam = this,
            onSend = { message ->
                viewModel.setMessage(message)
            }, onClose = {
                viewModel.setMessage("")
            }
        )
        loadingDialog = LoadingDialog(this)
        notificationDialog = NotificationDialog(
            context = this,
            onOk = {
                startActivity(Intent(thiscom.datn.bia.a.present.activity.order.confirm.ConfirmOrderActivity, OrderActivity::class.java))
                finish()
            }
        )
        setData()
    }
//        `feat: thêm ConfirmOrderActivity xử lý xác nhận đơn hàng và thanh toán`
override fun onClickViews() {
    super.onClickViews()

    binding.btnPay.click {
        paymentOrder()
    }

    binding.icBack.click {
        finish()
    }

    binding.btnShowMessage.click {
        messageDialog?.show()
    }
}

    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.message.collect { message ->
                binding.tvMessage.text = message
            }
        }

        lifecycleScope.launch {
            viewModel.stateCheckOut.collect { uiState ->
                when (uiState) {
                    is UiState.Error -> {
                        Log.d("duylt", "Message: ${uiState.message}")
                        showToastOnce(getString(R.string.msg_ins_stock))
                        loadingDialog?.cancel()
                        viewModel.changeStateToIdle()
                    }

                    UiState.Idle -> {

                    }

                    UiState.Loading -> {
                        loadingDialog?.show()
                    }

                    is UiState.Success<*> -> {
                        loadingDialog?.cancel()

                        notificationDialog?.show()
                    }
                }
            }
        }
    }
    //`feat: bổ sung xử lý sự kiện và quan sát trạng thái thanh toán trong ConfirmOrderActivity`
    override fun onDestroy() {
        gson = null
        cartConfirmAdapter?.list?.clear()
        cartConfirmAdapter = null
        messageDialog?.dismiss()
        messageDialog = null
        loadingDialog?.dismiss()
        loadingDialog = null

        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        ZaloPaySDK.getInstance().onResult(intent)
    }

    private fun initZaloPay() {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        // ZaloPay SDK Init
        ZaloPaySDK.init(553, Environment.SANDBOX)
    }
    //`feat: bổ sung xử lý vòng đời và tích hợp kết quả thanh toán ZaloPay trong ConfirmOrderActivity`

}