package com.majelan.androidtechnicaltest.features.player.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.media3.common.MediaItem
import coil.compose.AsyncImage
import com.majelan.androidtechnicaltest.R
import com.majelan.androidtechnicaltest.common.domain.entity.MediaEntity
import com.majelan.androidtechnicaltest.common.presentation.DisplayableText
import com.majelan.androidtechnicaltest.common.presentation.Error
import com.majelan.androidtechnicaltest.common.presentation.Loading
import com.majelan.androidtechnicaltest.ui.values.basePadding
import com.majelan.androidtechnicaltest.ui.values.iconSize
import com.majelan.androidtechnicaltest.ui.values.titleFontSize

@Composable
fun PlayerComposable(
    mediaEntity: MediaEntity?,
    viewModel: PlayerViewModel,
) {
    if (mediaEntity != null) {
        Player(mediaEntity, viewModel)
    } else {
        Error()
    }
}

@Composable
fun Player(media: MediaEntity, viewModel: PlayerViewModel) {
    val playerState: PlayerState by viewModel.playerState.collectAsState()

    Column {
        Column(modifier = Modifier.weight(8F)) {
            AsyncImage(
                model = media.image,
                contentDescription = media.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(basePadding))
        Column(modifier = Modifier.weight(2F)) {
            Text(
                text = media.title,
                style = TextStyle(fontSize = titleFontSize),
                modifier = Modifier.padding(start = basePadding)
            )
            Text(
                text = media.artist,
                modifier = Modifier.padding(start = basePadding),
            )
        }
        Column(modifier = Modifier.weight(2F)) {
            Row(
                modifier = Modifier.padding(start = basePadding, end = basePadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MediaPlayer(
                    playerState = playerState,
                    viewModel = viewModel,
                    media = media,
                    modifier = Modifier.weight(1F)
                )
                Row(
                    modifier = Modifier
                        .padding(start = basePadding, end = basePadding)
                        .weight(3F),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val time = viewModel.timeState.collectAsState()
                    Text(text = time.value.timePlayed)
                    Text(text = time.value.duration)
                }
            }
        }
    }
}

@Composable
fun MediaPlayer(
    playerState: PlayerState,
    viewModel: PlayerViewModel,
    media: MediaEntity,
    modifier: Modifier
) {
    when (playerState) {
        PlayerState.Init -> {
            viewModel.setMedia(MediaItem.fromUri(media.source), LocalContext.current)
            Loading(modifier = modifier)
        }

        PlayerState.Ready -> {
            viewModel.playMedia()
            DisplayableText(text = "Playing...", modifier = modifier)
        }

        PlayerState.Play -> {
            Button(onClick = { viewModel.pauseMedia() }, modifier = modifier) {
                Icon(
                    painter = painterResource(id = R.drawable.pause),
                    modifier = Modifier.size(iconSize),
                    contentDescription = "Pause"
                )
            }
        }

        PlayerState.Pause -> {
            Button(onClick = { viewModel.playMedia() }, modifier = modifier) {
                Icon(
                    painter = painterResource(id = R.drawable.play),
                    modifier = Modifier.size(iconSize),
                    contentDescription = "Play"
                )
            }
        }

        else -> Error(modifier = modifier)
    }
}