package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class Forecast3day(
    @SerializedName("grid") val grid: Grid,
    @SerializedName("timeRelease") val timeRelease: String,
    @SerializedName("fcst3hour") val fcst3hour: Fcst3hour,
    @SerializedName("fcst6hour") val fcst6hour: Fcst6hour,
    @SerializedName("fcstdaily") val fcstdaily: Fcstdaily
)