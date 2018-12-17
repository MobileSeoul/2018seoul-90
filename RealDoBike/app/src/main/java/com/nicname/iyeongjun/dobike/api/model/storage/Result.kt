package com.nicname.iyeongjun.dobike.api.model.storage

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id") val id: Int,
        @SerializedName("section") val section: String,
        @SerializedName("num") val num: Int,
        @SerializedName("title") val title: String,
        @SerializedName("location") val location: String,
        @SerializedName("quantity") val quantity: Int,
        @SerializedName("lat") val lat: Double,
        @SerializedName("lon") val lon: Double
)