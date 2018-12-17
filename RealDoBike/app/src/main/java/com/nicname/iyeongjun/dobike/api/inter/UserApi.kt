package com.nicname.iyeongjun.dobike.api.inter

import com.nicname.iyeongjun.dobike.api.model.user.UserModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi{
    @GET("user/")
    fun signin(

    ) : Observable<UserModel>

    @POST("user/")
    fun signup(
            @Body json : JSONObject
    ) : Call<ResponseBody>
}