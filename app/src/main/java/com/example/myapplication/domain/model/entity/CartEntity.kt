package com.example.myapplication.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.model.entity.CartEntity

@Entity(tableName = "CartEntity")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val idProd: String = "",
    val idUser: String = "",
    val quantity: Int = 0,
    val isEnable: Boolean = false,
    val price: Double,

    val variant: ResVariantDTO
) {
}
//feat: thêm entity CartEntity cho giỏ hàng