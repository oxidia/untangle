package com.youftek.untangle.presentation.game_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youftek.untangle.domain.repository.DictionaryRepository
import com.youftek.untangle.domain.repository.WordsRepository
import com.youftek.untangle.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val wordsRepository: WordsRepository,
    private val dictionaryRepository: DictionaryRepository,
) : ViewModel() {

    var state by mutableStateOf(GameUiState())
        private set

    init {
        initTheWordOfToday()
    }

    fun updateUserGuess(guess: String) {
        state = state.copy(
            userGuess = guess
        )
    }

    fun checkUserGuess() {
        val userGuess = state.userGuess.trim().lowercase()

        state = if (userGuess != state.wordOfTheDay) {
            state.copy(
                triesCount = state.triesCount + 1,
                isGuessedWordWrong = true,
            )
        } else {
            state.copy(
                isGameOver = true,
                isGuessedWordWrong = false,
                isDialogVisible = true,
            )
        }
    }

    fun hideDialog() {
        state = state.copy(
            isDialogVisible = false,
        )
    }

    private fun initTheWordOfToday() {
        wordsRepository.getTodayWord()
            .onEach {
                when (it) {
                    is Resource.IsLoading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        val word = it.data!!.word
                        val charArray = word.toCharArray()

                        charArray.shuffle()

                        state = state.copy(
                            wordOfTheDay = word,
                            currentScrambledWord = String(charArray),
                            isLoading = false
                        )

                        initWordOverview(word)
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            errorMessage = it.message ?: "Unexpected Error",
                            isLoading = false
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun initWordOverview(word: String) {
        dictionaryRepository.getWordOverview(word)
            .onEach {
                when (it) {
                    is Resource.Success -> {
                        state = state.copy(
                            wordOverview = it.data
                        )
                    }

                    else -> Unit
                }

            }
            .launchIn(viewModelScope)
    }
}
