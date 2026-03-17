package com.example.myapplication.domain.model.domain

import com.example.myapplication.R

data class SettingCat(
    var titleRes: Int = 0,
    val items: List<SettingItem> = emptyList(),
    val type: TypeSettingItem = TypeSettingItem.MY_ACCOUNT
) {
    companion object {
        fun getAllSettingsCat() = listOf(
            SettingCat(R.string.my_account, SettingItem.getSettingItemTypeAccount()),
            SettingCat(R.string.setting, SettingItem.getSettingItemTypeSetting(), TypeSettingItem.SETTING),
            SettingCat(R.string.support, SettingItem.getSettingItemTypeHelp(), TypeSettingItem.HELP),
        )
    }
}
enum class TypeSettingItem() {
    MY_ACCOUNT,
    SETTING,
    HELP
}
