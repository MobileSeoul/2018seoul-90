package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class Fcstdaily(
    @SerializedName("temperature") val temperature: Temperature
)