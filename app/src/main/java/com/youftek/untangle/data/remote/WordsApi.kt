package com.youftek.untangle.data.remote

import com.youftek.untangle.data.remote.dto.TodayWordDto
import retrofit2.http.GET

interface WordsApi {
    @GET("word-of-the-day")
    suspend fun getTodayWord(): TodayWordDto
}
