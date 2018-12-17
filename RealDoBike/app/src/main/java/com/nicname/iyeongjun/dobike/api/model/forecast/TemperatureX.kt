package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class TemperatureX(
    @SerializedName("temp4hour") val temp4hour: String,
    @SerializedName("temp7hour") val temp7hour: String,
    @SerializedName("temp10hour") val temp10hour: String,
    @SerializedName("temp13hour") val temp13hour: String,
    @SerializedName("temp16hour") val temp16hour: String,
    @SerializedName("temp19hour") val temp19hour: String,
    @SerializedName("temp22hour") val temp22hour: String,
    @SerializedName("temp25hour") val temp25hour: String,
    @SerializedName("temp28hour") val temp28hour: String,
    @SerializedName("temp31hour") val temp31hour: String,
    @SerializedName("temp34hour") val temp34hour: String,
    @SerializedName("temp37hour") val temp37hour: String,
    @SerializedName("temp40hour") val temp40hour: String,
    @SerializedName("temp43hour") val temp43hour: String,
    @SerializedName("temp46hour") val temp46hour: String,
    @SerializedName("temp49hour") val temp49hour: String,
    @SerializedName("temp52hour") val temp52hour: String,
    @SerializedName("temp55hour") val temp55hour: String,
    @SerializedName("temp58hour") val temp58hour: String,
    @SerializedName("temp61hour") val temp61hour: String,
    @SerializedName("temp64hour") val temp64hour: String,
    @SerializedName("temp67hour") val temp67hour: String
)