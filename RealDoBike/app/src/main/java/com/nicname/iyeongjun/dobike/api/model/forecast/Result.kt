package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("code") val code: Int,
        @SerializedName("requestUrl") val requestUrl: String,
        @SerializedName("message") val message: String
)