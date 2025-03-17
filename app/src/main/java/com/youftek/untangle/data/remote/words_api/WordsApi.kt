package com.youftek.untangle.data.remote.words_api

import com.youftek.untangle.data.remote.words_api.dto.TodayWordDto
import retrofit2.http.GET

interface WordsApi {
    @GET("word-of-the-day")
    suspend fun getTodayWord(): TodayWordDto
}
