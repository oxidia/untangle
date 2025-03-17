package com.youftek.untangle.data.repository

import android.util.Log
import com.youftek.untangle.data.mappers.toWordOverview
import com.youftek.untangle.data.remote.dictionary_api.DictionaryApi
import com.youftek.untangle.domain.model.WordOverview
import com.youftek.untangle.domain.repository.DictionaryRepository
import com.youftek.untangle.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DictionaryRepositoryImpl(
    private val dictionaryApi: DictionaryApi,
) : DictionaryRepository {

    override fun getWordOverview(word: String): Flow<Resource<WordOverview>> =
        flow {
            try {
                emit(Resource.IsLoading())
                val wordOverviewDto = dictionaryApi.getWordOverview(word)

                emit(Resource.Success(
                    data = wordOverviewDto.toWordOverview()
                ))
            } catch (e: Exception) {
                Log.d("DEBUG", e.stackTraceToString())
                emit(Resource.Error(
                    message = e.message ?: "Unexpected Error",
                ))
            }
        }
}
