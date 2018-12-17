package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("forecast3days") val forecast3days: List<Forecast3day>
)