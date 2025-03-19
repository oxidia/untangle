package com.youftek.untangle.presentation.game_screen.components

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youftek.untangle.presentation.game_screen.SQUARE_SIZE

data class Letter(
    val value: Char,
    val animatedState: State<IntOffset>,
)

@Composable
fun LetterItem(
    value: Char,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = value.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun AnimatedWord(
    xPositions: List<Int>,
    word: String,
    isShuffled: Boolean,
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit,
) {
    val letters = word.toCharArray()
        .mapIndexed { index, letter ->
            val position = xPositions[index]
            val origin = IntOffset(
                x = with(LocalDensity.current) { (index * SQUARE_SIZE).dp.roundToPx() },
                y = 0
            )
            val target = IntOffset(x = with(LocalDensity.current) {
                position.dp.roundToPx()
            }, y = 0)

            Letter(
                value = letter,
                animatedState = animateIntOffsetAsState(
                    finishedListener = { onAnimationFinished() },
                    label = "offset",
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = EaseIn
                    ),
                    targetValue = if (isShuffled) {
                        target
                    } else {
                        origin
                    }
                )
            )
        }

    Box(
        modifier = modifier.width((SQUARE_SIZE * word.length).dp)
    ) {
        letters.forEach { letter ->
            LetterItem(
                modifier = Modifier
                    .offset { letter.animatedState.value }
                    .size(SQUARE_SIZE.dp),
                value = letter.value
            )
        }
    }
}
