package com.youftek.untangle.data.repository

import com.youftek.untangle.data.mappers.toTodayWord
import com.youftek.untangle.data.remote.words_api.WordsApi
import com.youftek.untangle.domain.model.TodayWord
import com.youftek.untangle.domain.repository.WordsRepository
import com.youftek.untangle.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordsRepositoryImpl(
    private val wordsApi: WordsApi,
) : WordsRepository {

    override fun getTodayWord(): Flow<Resource<TodayWord>> {
        return flow {
            try {
                emit(Resource.IsLoading())

                val todayWord = wordsApi.getTodayWord()
                    .toTodayWord()

                emit(Resource.Success(todayWord))
            } catch (e: Exception) {
                emit(
                    Resource.Error(message = e.message ?: "Unexpected error")
                )
            }
        }
    }
}
