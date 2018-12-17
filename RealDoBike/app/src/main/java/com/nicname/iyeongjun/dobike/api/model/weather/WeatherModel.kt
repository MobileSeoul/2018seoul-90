package com.nicname.iyeongjun.dobike.api.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("weather") val weather: Weather?,
    @SerializedName("common") val common: Common?,
    @SerializedName("result") val result: Result?
)