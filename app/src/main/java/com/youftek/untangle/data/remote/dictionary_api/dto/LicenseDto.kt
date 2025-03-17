package com.youftek.untangle.data.remote.dictionary_api.dto


import com.google.gson.annotations.SerializedName

data class LicenseDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
