package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class Common(
        @SerializedName("alertYn") val alertYn: String,
        @SerializedName("stormYn") val stormYn: String
)