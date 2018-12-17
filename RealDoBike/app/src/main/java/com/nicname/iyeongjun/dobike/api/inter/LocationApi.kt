package com.nicname.iyeongjun.dobike.api.inter

import com.nicname.iyeongjun.dobike.api.model.location.LocationModel
import io.reactivex.Observable
import retrofit2.http.GET

interface LocationApi{
    @GET("location/")
    fun getLocation(

    ):Observable<LocationModel>
}