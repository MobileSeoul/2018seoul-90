package com.nicname.iyeongjun.dobike.api.model.forecast

import com.google.gson.annotations.SerializedName

data class Humidity(
    @SerializedName("rh4hour") val rh4hour: String,
    @SerializedName("rh64hour") val rh64hour: String,
    @SerializedName("rh67hour") val rh67hour: String,
    @SerializedName("rh7hour") val rh7hour: String,
    @SerializedName("rh10hour") val rh10hour: String,
    @SerializedName("rh13hour") val rh13hour: String,
    @SerializedName("rh16hour") val rh16hour: String,
    @SerializedName("rh19hour") val rh19hour: String,
    @SerializedName("rh22hour") val rh22hour: String,
    @SerializedName("rh25hour") val rh25hour: String,
    @SerializedName("rh28hour") val rh28hour: String,
    @SerializedName("rh31hour") val rh31hour: String,
    @SerializedName("rh34hour") val rh34hour: String,
    @SerializedName("rh37hour") val rh37hour: String,
    @SerializedName("rh40hour") val rh40hour: String,
    @SerializedName("rh43hour") val rh43hour: String,
    @SerializedName("rh46hour") val rh46hour: String,
    @SerializedName("rh49hour") val rh49hour: String,
    @SerializedName("rh52hour") val rh52hour: String,
    @SerializedName("rh55hour") val rh55hour: String,
    @SerializedName("rh58hour") val rh58hour: String,
    @SerializedName("rh61hour") val rh61hour: String
)