package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("tmax1day") val tmax1day: String,
    @SerializedName("tmax2day") val tmax2day: String,
    @SerializedName("tmax3day") val tmax3day: String,
    @SerializedName("tmin1day") val tmin1day: String,
    @SerializedName("tmin2day") val tmin2day: String,
    @SerializedName("tmin3day") val tmin3day: String
)