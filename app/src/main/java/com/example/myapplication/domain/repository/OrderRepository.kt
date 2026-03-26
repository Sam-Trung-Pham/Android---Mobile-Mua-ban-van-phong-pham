package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.dto.req.ReqCancelOrder
import com.example.myapplication.domain.model.dto.req.ReqCheckOutDTO
import com.example.myapplication.domain.model.dto.req.ReqUpdateOrder
import com.example.myapplication.domain.model.dto.res.ResAllOrder
import com.example.myapplication.domain.model.dto.res.ResCheckOutDTO
import com.example.myapplication.domain.model.dto.res.ResOrderDTO
import com.example.myapplication.domain.model.dto.res.ResUpdateOrder
import retrofit2.http.Path
interface OrderRepository {
    suspend fun checkOut(
        reqCheckOutDTO: ReqCheckOutDTO
    ): ResultWrapper<ResCheckOutDTO>
    //`feat: thêm interface OrderRepository cho chức năng đặt hàng`
    
}