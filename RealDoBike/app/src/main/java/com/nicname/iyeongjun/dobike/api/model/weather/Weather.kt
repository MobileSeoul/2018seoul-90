package com.nicname.iyeongjun.dobike.api.model.weather

import com.google.gson.annotations.SerializedName
data class Weather(
    @SerializedName("hourly") val hourly: List<Hourly?>?
)