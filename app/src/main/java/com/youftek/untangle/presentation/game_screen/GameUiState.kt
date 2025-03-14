package com.youftek.untangle.presentation.game_screen

data class GameUiState(
    val isLoading: Boolean = false,
    val wordOfTheDay: String = "",
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val triesCount: Int = 0,
    val userGuess: String = "",
    val errorMessage: String = "",
    val isGameOver: Boolean = false,
)
