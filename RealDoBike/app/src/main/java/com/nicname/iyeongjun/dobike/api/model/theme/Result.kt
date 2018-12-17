package com.nicname.iyeongjun.dobike.api.model.theme

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id") val id: Int,
        @SerializedName("number") val number: Int,
        @SerializedName("theme") val theme: String,
        @SerializedName("introduce") val introduce: String,
        @SerializedName("total_distance") val totalDistance: String,
        @SerializedName("total_time") val totalTime: String,
        @SerializedName("tag") val tag: String,
        @SerializedName("image") val image: String,
        @SerializedName("type") val type: String
)