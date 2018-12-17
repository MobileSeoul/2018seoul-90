package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @SerializedName("weather") val weather: Weather,
    @SerializedName("common") val common: Common,
    @SerializedName("result") val result: Result
)