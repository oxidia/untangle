package com.youftek.untangle.presentation.game_screen

import com.youftek.untangle.domain.model.WordOverview

data class GameUiState(
    val isLoading: Boolean = false,
    val wordOfTheDay: String = "",
    val wordOverview: WordOverview? = null,
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val triesCount: Int = 0,
    val userGuess: String = "",
    val errorMessage: String = "",
    val isGameOver: Boolean = false,
    val isDialogVisible: Boolean = false,
    val randomXPositions: List<Int> = emptyList(),
)
