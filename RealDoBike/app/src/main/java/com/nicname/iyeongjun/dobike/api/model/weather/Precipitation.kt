package com.nicname.iyeongjun.dobike.api.model.weather

import com.google.gson.annotations.SerializedName
data class Precipitation(
    @SerializedName("sinceOntime") val sinceOntime: String?,
    @SerializedName("type") val type: String?
)