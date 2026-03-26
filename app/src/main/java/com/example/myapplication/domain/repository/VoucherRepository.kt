package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.dto.res.ResVoucherDTO

interface VoucherRepository {
    suspend fun fetchAllVouchers(): ResultWrapper<List<ResVoucherDTO>>
}
//`feat: thêm interface VoucherRepository lấy danh sách mã giảm giá`