package com.example.myapplication.domain.repository
import com.example.myapplication.domain.model.dto.req.ReqForgotPass
import com.example.myapplication.domain.model.dto.req.ReqLoginUserDTO
import com.example.myapplication.domain.model.dto.req.ReqResetPass
import com.example.myapplication.domain.model.dto.req.ReqSignUpUserDTO
import com.example.myapplication.domain.model.dto.req.ReqUpdateAddressDTO
import com.example.myapplication.domain.model.dto.req.ReqUpdatePhoneDTO
import com.example.myapplication.domain.model.dto.res.ResForgotPass
import com.example.myapplication.domain.model.dto.res.ResLoginUserDTO
import com.example.myapplication.domain.model.dto.res.ResResetPass
import com.example.myapplication.domain.model.dto.res.ResSignUpUserDTO
import com.example.myapplication.domain.model.dto.res.ResUpdatePhoneDTO
interface AuthRepository {
    suspend fun loginUser(
        req: ReqLoginUserDTO
    ): ResultWrapper<ResLoginUserDTO>

    suspend fun signUpUser(
        req: ReqSignUpUserDTO
    ): ResultWrapper<ResSignUpUserDTO>
    //`feat: thêm interface AuthRepository cho đăng nhập và đăng ký người dùng`
    suspend fun updatePhoneNumber(
        orderId: String,
        req: ReqUpdatePhoneDTO
    ): ResultWrapper<ResUpdatePhoneDTO>

    suspend fun updateAddress(
        id: String,
        req: ReqUpdateAddressDTO
    ): ResultWrapper<ResUpdatePhoneDTO>

    suspend fun forgotPassword(
        req: ReqForgotPass
    ): ResultWrapper<ResForgotPass>

    suspend fun resetPassword(
        req: ReqResetPass,
        token: String
    ): ResultWrapper<ResResetPass>
    //`feat: bổ sung chức năng cập nhật thông tin và khôi phục mật khẩu trong AuthRepository`
}