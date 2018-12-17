package com.nicname.iyeongjun.dobike.api.model.weather

import com.google.gson.annotations.SerializedName
data class Wind(
    @SerializedName("wdir") val wdir: String?,
    @SerializedName("wspd") val wspd: String?
)