package com.nicname.iyeongjun.dobike.api.model.location

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id") val id: Int,
        @SerializedName("number") val number: Int,
        @SerializedName("course_name") val courseName: String,
        @SerializedName("course_introduce") val courseIntroduce: String,
        @SerializedName("course_image") val courseImage: String,
        @SerializedName("course_lat") val courseLat: Double,
        @SerializedName("course_lon") val courseLon: Double,
        @SerializedName("number_order") val numberOrder: String
)