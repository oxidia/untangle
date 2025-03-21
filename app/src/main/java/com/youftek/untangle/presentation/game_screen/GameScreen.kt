package com.youftek.untangle.presentation.game_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.youftek.untangle.R
import com.youftek.untangle.presentation.game_screen.components.GameCard
import com.youftek.untangle.presentation.game_screen.components.WordOverviewDisplay

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel(),
) {
    val gameUiState = gameViewModel.state
    val mediumPadding = 16.dp

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.app_name),
            style = typography.titleLarge,
        )
        GameCard(
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            retriesCount = gameUiState.triesCount,
            userGuess = gameUiState.userGuess,
            onKeyboardDone = {
                if (gameUiState.isGameOver) {
                    gameViewModel.showDialog()
                } else {
                    gameViewModel.checkUserGuess()
                }
            },
            word = gameUiState.wordOfTheDay,
            isGameOver = gameUiState.isGameOver,
            randomXPositions = gameUiState.randomXPositions,
            isGuessWrong = gameUiState.isGuessedWordWrong,
            isLoading = gameUiState.isLoading,
            onAnimationFinished = { gameViewModel.showDialog() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(mediumPadding)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(mediumPadding),
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                enabled = !gameUiState.isLoading,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (gameUiState.isGameOver) {
                        gameViewModel.showDialog()
                    } else {
                        gameViewModel.checkUserGuess()
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.verify),
                    fontSize = 16.sp
                )
            }
        }
    }

    if (gameUiState.isGameOver && gameUiState.isDialogVisible) {
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = true,
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = {
                gameViewModel.hideDialog()
            },
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp,
                    )
            ) {
                Column(
                    modifier = Modifier.background(
                        color = Color.White,
                    )
                ) {
                    WordOverviewDisplay(
                        wordOverview = gameUiState.wordOverview!!,
                        onBackClick = {
                            gameViewModel.hideDialog()
                        }
                    )
                }
            }
        }
    }
}
