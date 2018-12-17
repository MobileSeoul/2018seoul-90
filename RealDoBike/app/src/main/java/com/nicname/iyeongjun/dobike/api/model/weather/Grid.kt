package com.nicname.iyeongjun.dobike.api.model.weather

import com.google.gson.annotations.SerializedName
data class Grid(
    @SerializedName("latitude") val latitude: String?,
    @SerializedName("longitude") val longitude: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("county") val county: String?,
    @SerializedName("village") val village: String?
)