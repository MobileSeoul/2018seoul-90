package com.nicname.iyeongjun.dobike.api.model.weather

import com.google.gson.annotations.SerializedName
data class Hourly(
    @SerializedName("grid") val grid: Grid?,
    @SerializedName("wind") val wind: Wind?,
    @SerializedName("precipitation") val precipitation: Precipitation?,
    @SerializedName("sky") val sky: Sky?,
    @SerializedName("temperature") val temperature: Temperature?,
    @SerializedName("humidity") val humidity: String?,
    @SerializedName("lightning") val lightning: String?,
    @SerializedName("sunRiseTime") val sunRiseTime: String?,
    @SerializedName("sunSetTime") val sunSetTime: String?,
    @SerializedName("timeRelease") val timeRelease: String?
)