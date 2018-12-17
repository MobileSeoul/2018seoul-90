package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class Fcst6hour(
        @SerializedName("rain6hour") val rain6hour: String,
        @SerializedName("rain12hour") val rain12hour: String,
        @SerializedName("rain18hour") val rain18hour: String,
        @SerializedName("rain24hour") val rain24hour: String,
        @SerializedName("rain30hour") val rain30hour: String,
        @SerializedName("rain36hour") val rain36hour: String,
        @SerializedName("rain42hour") val rain42hour: String,
        @SerializedName("rain48hour") val rain48hour: String,
        @SerializedName("rain54hour") val rain54hour: String,
        @SerializedName("snow6hour") val snow6hour: String,
        @SerializedName("snow12hour") val snow12hour: String,
        @SerializedName("snow18hour") val snow18hour: String,
        @SerializedName("snow24hour") val snow24hour: String,
        @SerializedName("snow30hour") val snow30hour: String,
        @SerializedName("snow36hour") val snow36hour: String,
        @SerializedName("snow42hour") val snow42hour: String,
        @SerializedName("snow48hour") val snow48hour: String,
        @SerializedName("snow54hour") val snow54hour: String,
        @SerializedName("rain60hour") val rain60hour: String,
        @SerializedName("rain66hour") val rain66hour: String,
        @SerializedName("snow60hour") val snow60hour: String,
        @SerializedName("snow66hour") val snow66hour: String
)