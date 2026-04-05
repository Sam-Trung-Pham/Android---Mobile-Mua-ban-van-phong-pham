package com.example.myapplication.present.activity.setting

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.base.ext.click
import com.example.myapplication.common.base.ext.goneView
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.common.base.ext.visibleView
import com.example.myapplication.data.storage.SharedPrefCommon
import com.example.myapplication.databinding.ActivitySettingBinding
import com.example.myapplication.domain.model.domain.SettingCat
import com.example.myapplication.domain.model.dto.res.ResLoginUserDTO
import com.example.myapplication.present.activity.fo.language.LanguageActivity
import com.example.myapplication.present.activity.home.MainActivity
import com.example.myapplication.present.activity.setting.adapter.SettingCatAdapter
import com.example.myapplication.present.dialog.LoadingDialog
import com.example.myapplication.present.dialog.UpdateDialog
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    private var settingCatAdapter: SettingCatAdapter? = null
    private var updatePhoneDialog: UpdateDialog? = null
    private var loadingDialog: LoadingDialog? = null
    private var updateAddressDialog: UpdateDialog? = null
    private var cachePhoneNumber: String = ""
    private var cacheAddress: String = ""

    private val viewModel: SettingViewModel by viewModels()

    override fun getLayoutActivity(): Int = R.layout.activity_setting

    override fun initViews() {
        super.initViews()

        if (SharedPrefCommon.jsonAcc.isEmpty()) {
            binding.btnLogOut.goneView()
        } else {
            binding.btnLogOut.visibleView()
        }

        Log.d("sampt", "Json: ${SharedPrefCommon.jsonAcc}")

        initRcvSetting()
        updatePhoneDialog = UpdateDialog(
            this,
            getString(R.string.update_phone),
            onUpdate = { message ->
                if (message.isEmpty()) {
                    return@UpdateDialog
                }

                cachePhoneNumber = message
                viewModel.updatePhone(message)
            }, onClose = {

            }
        )
        updateAddressDialog = UpdateDialog(
            this,
            getString(R.string.update_address),
            onUpdate = { address ->
                if (address.isEmpty()) {
                    return@UpdateDialog
                }

                cacheAddress = address
                viewModel.updateAddress(address)
            }, onClose = {}
        )
        loadingDialog = LoadingDialog(this)
    }
    //`feat: thêm SettingActivity và xử lý khởi tạo danh sách, dialog cập nhật thông tin`
    override fun onClickViews() {
        super.onClickViews()

        binding.icBack.click { finish() }

        binding.icChat.click {

        }

        binding.btnLogOut.click {
            SharedPrefCommon.jsonAcc = ""
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
    }
    //`feat: bổ sung xử lý đăng xuất và sự kiện click trong SettingActivity`
    override fun observerData() {
        super.observerData()

        lifecycleScope.launch {
            viewModel.stateUpdatePhone.collect { state ->
                when (state) {
                    is UiState.Error -> {
                        loadingDialog?.cancel()
                        viewModel.changeStateToIdle()
                    }

                    UiState.Idle -> {

                    }

                    UiState.Loading -> {
                        loadingDialog?.show()
                    }

                    is UiState.Success<*> -> {
                        Gson().fromJson(SharedPrefCommon.jsonAcc, ResLoginUserDTO::class.java)
                            ?.let {
                                val newRes = it.copy(
                                    user = it.user?.copy(
                                        phone = cachePhoneNumber
                                    )
                                )

                                SharedPrefCommon.jsonAcc = Gson().toJson(newRes)
                            }

                        showToastOnce(getString(R.string.update_success))
                        loadingDialog?.cancel()

                        viewModel.changeStateToIdle()
                    }
                }
            }
        }
        //`feat: bổ sung xử lý cập nhật số điện thoại trong SettingActivity`
        lifecycleScope.launch {
            viewModel.stateUpdateAddress.collect { state ->
                when (state) {
                    is UiState.Error -> {
                        loadingDialog?.cancel()
                        viewModel.changeStateAddressToIdle()
                    }

                    UiState.Idle -> {

                    }

                    UiState.Loading -> {
                        loadingDialog?.show()
                    }

                    is UiState.Success<*> -> {
                        Gson().fromJson(SharedPrefCommon.jsonAcc, ResLoginUserDTO::class.java)
                            ?.let {
                                val newRes = it.copy(
                                    user = it.user?.copy(
                                        address = cacheAddress
                                    )
                                )

                                SharedPrefCommon.jsonAcc = Gson().toJson(newRes)
                            }

                        showToastOnce(getString(R.string.update_success))
                        loadingDialog?.cancel()

                        viewModel.changeStateAddressToIdle()
                    }
                }
            }
        }
    }
    //`feat: bổ sung xử lý cập nhật địa chỉ trong SettingActivity`
    override fun onDestroy() {
        updatePhoneDialog?.dismiss()
        updatePhoneDialog = null

        super.onDestroy()
    }

    private fun initRcvSetting() = binding.rcvSettingCat.apply {
        settingCatAdapter = SettingCatAdapter(
            contextParams = this@SettingActivity,
            onSettingItem = { index, setting ->
                when (setting.id) {
                    0 -> {

                    }

                    1 -> updateAddressDialog?.show()

                    2 -> updatePhoneDialog?.show()

                    10 ->
                        startActivity(Intent(this@SettingActivity, LanguageActivity::class.java))
                }
            }
        ).apply {
            submitData(SettingCat.getAllSettingsCat())
        }

        adapter = settingCatAdapter
    }
    //`feat: bổ sung khởi tạo danh sách cài đặt và xử lý chọn mục trong SettingActivity`
}