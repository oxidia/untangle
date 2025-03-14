package com.youftek.untangle.domain.repository

import com.youftek.untangle.domain.TodayWord
import com.youftek.untangle.util.Resource
import kotlinx.coroutines.flow.Flow


interface WordsRepository {
    fun getTodayWord(): Flow<Resource<TodayWord>>
}
