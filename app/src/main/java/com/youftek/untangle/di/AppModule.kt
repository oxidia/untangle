package com.youftek.untangle.di

import com.youftek.untangle.data.remote.WordsApi
import com.youftek.untangle.data.repository.WordsRepositoryImpl
import com.youftek.untangle.domain.repository.WordsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWordsApi(): WordsApi {
        return Retrofit.Builder()
            .baseUrl("https://words.dev-apis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WordsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordsRepository(wordsApi: WordsApi): WordsRepository {
        return WordsRepositoryImpl(
            wordsApi = wordsApi
        )
    }
}
