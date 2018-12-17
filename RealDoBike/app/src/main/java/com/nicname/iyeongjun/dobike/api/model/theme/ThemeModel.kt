package com.nicname.iyeongjun.dobike.api.model.theme

import com.google.gson.annotations.SerializedName

data class ThemeModel(
        @SerializedName("count") val count: Int,
        @SerializedName("next") val next: Any?,
        @SerializedName("previous") val previous: Any?,
        @SerializedName("results") val results: List<Result>
)