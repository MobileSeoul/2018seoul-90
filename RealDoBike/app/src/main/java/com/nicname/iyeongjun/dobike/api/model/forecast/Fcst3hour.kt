package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class Fcst3hour(
    @SerializedName("wind") val wind: Wind,
    @SerializedName("precipitation") val precipitation: Precipitation,
    @SerializedName("sky") val sky: Sky,
    @SerializedName("temperature") val temperature: TemperatureX,
    @SerializedName("humidity") val humidity: Humidity
)