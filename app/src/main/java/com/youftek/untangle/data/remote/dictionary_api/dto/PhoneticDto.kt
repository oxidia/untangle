package com.youftek.untangle.data.remote.dictionary_api.dto


import com.google.gson.annotations.SerializedName

data class PhoneticDto(
    @SerializedName("audio")
    val audio: String,
    @SerializedName("license")
    val license: LicenseDto,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("text")
    val text: String?
)
