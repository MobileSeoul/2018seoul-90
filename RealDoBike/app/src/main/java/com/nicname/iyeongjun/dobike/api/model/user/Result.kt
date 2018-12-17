package com.nicname.iyeongjun.dobike.api.model.user

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id") val id: Int,
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String,
        @SerializedName("name") val name: String
)