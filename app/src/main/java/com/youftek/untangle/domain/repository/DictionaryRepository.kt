package com.youftek.untangle.domain.repository

import com.youftek.untangle.domain.model.WordOverview
import com.youftek.untangle.util.Resource
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    fun getWordOverview(word: String): Flow<Resource<WordOverview>>
}
