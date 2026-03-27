package com.example.myapplication.model.domain

import com.example.myapplication.R

data class SettingCat(
    var titleRes: Int = 0,
    val items: List<com.datn.bia.a.model.domain.SettingItem> = emptyList(),
    val type: TypeSettingItem = TypeSettingItem.MY_ACCOUNT
) {
    companion object {
        fun getAllSettingsCat() = listOf(
            SettingCat(R.string.my_account, com.datn.bia.a.model.domain.SettingItem.getSettingItemTypeAccount()),
            SettingCat(R.string.setting, com.datn.bia.a.model.domain.SettingItem.getSettingItemTypeSetting(), TypeSettingItem.SETTING),
            SettingCat(R.string.support, com.datn.bia.a.model.domain.SettingItem.getSettingItemTypeHelp(), TypeSettingItem.HELP),
        )
    }
}

data class SettingItem(
    val id: Int = 0,
    val titleRes: Int = 0,
    val cat: TypeSettingItem = TypeSettingItem.MY_ACCOUNT
    //`feat: thêm model SettingCat và SettingItem cho danh mục cài đặt`
) {

}