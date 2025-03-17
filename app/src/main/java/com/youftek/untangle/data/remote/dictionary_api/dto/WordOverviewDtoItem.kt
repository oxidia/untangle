package com.youftek.untangle.data.remote.dictionary_api.dto


import com.google.gson.annotations.SerializedName

data class WordOverviewDtoItem(
    @SerializedName("license")
    val license: LicenseDto,
    @SerializedName("meanings")
    val meanings: List<MeaningDto>,
    @SerializedName("phonetic")
    val phonetic: String,
    @SerializedName("phonetics")
    val phonetics: List<PhoneticDto>,
    @SerializedName("sourceUrls")
    val sourceUrls: List<String>,
    @SerializedName("word")
    val word: String
)
