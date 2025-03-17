package com.youftek.untangle.presentation.game_screen.components

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youftek.untangle.R
import com.youftek.untangle.domain.model.Definition
import com.youftek.untangle.domain.model.Meaning
import com.youftek.untangle.domain.model.WordOverview


@Composable
fun WordOverviewDisplay(
    wordOverview: WordOverview,
    onBackClick: () -> Unit,
) {
    val isPlayButtonEnabled = wordOverview.phonetic.audio != null
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    LaunchedEffect(isPlayButtonEnabled) {
        if (isPlayButtonEnabled) {
            val player = MediaPlayer()

            player.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )

            player.setDataSource(wordOverview.phonetic.audio)
            player.prepare()
            mediaPlayer = player
        }
    }

    Column {
        Row {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    tint = Color.Black,
                    contentDescription = "Close dialog"
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    mediaPlayer?.start()
                },
                enabled = wordOverview.phonetic.audio != null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        color = Color(0xFFA8C7FA)
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.speaker_icon),
                    tint = Color.Black,
                    contentDescription = "Speaker icon"
                )
            }

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = wordOverview.word,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = wordOverview.phonetic.text
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Column {
            wordOverview.meanings.forEach { meaning ->
                Section(
                    meaning = meaning,
                )
            }
        }
    }
}

@Composable
fun Section(
    meaning: Meaning,
) {
    Column {
        Text(
            text = meaning.partOfSpeech,
            fontStyle = FontStyle.Italic,
            color = Color.Gray
        )
        Spacer(Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 12.dp
                )
        ) {
            meaning.definitions.take(2)
                .forEachIndexed { index, definition ->
                    SectionItem(
                        definition = definition,
                        number = index + 1
                    )
                    Spacer(Modifier.height(12.dp))
                }
        }
    }
}

@Composable
fun SectionItem(
    definition: Definition,
    number: Int,
) {
    Column {
        Row {
            Text(
                text = "$number. ",
            )

            Column {
                Text(
                    text = definition.definition
                )
                if (definition.example != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "\"${definition.example}\"",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
