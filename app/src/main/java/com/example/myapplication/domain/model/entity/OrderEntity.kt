package com.example.myapplication.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.model.entity.OrderEntity

@Entity(tableName = "OrderEntity")
data class OrderEntity(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val madh: Int,
    val customerName: String,
    val totalPrice: Double,
    val phone: String,
    val address: String,
    val products: List<OrderProduct>,
    val status: String,
    val payment: String,
    val userId: String,
    val voucherId: String,
    val note: String,
    val orderDate: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
)
//`feat: thêm entity OrderEntity cho đơn hàng`