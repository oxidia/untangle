package com.youftek.untangle.data.remote.dictionary_api

import com.youftek.untangle.data.remote.dictionary_api.dto.WordOverviewDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("v2/entries/en/{word}")
    suspend fun getWordOverview(@Path("word") word: String): WordOverviewDto
}
