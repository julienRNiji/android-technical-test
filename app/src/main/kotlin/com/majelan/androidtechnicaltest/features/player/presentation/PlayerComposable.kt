package com.majelan.androidtechnicaltest.features.player.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import com.majelan.androidtechnicaltest.common.domain.entity.MediaEntity
import com.majelan.androidtechnicaltest.ui.values.basePadding
import com.majelan.androidtechnicaltest.ui.values.titleFontSize


// TODO THIS PAGE IS WIP
@Composable
fun PlayerComposable(mediaEntity: MediaEntity?) {
    if (mediaEntity != null) {
        Player(mediaEntity)
    } else {
        Text(
            text = "Error while loading",
            style = TextStyle(
                fontSize = titleFontSize
            )
        )
    }
}

@Composable
fun Player(media: MediaEntity) {
    val player = ExoPlayer.Builder(LocalContext.current).build().apply {
        addMediaItem(MediaItem.fromUri(media.source))
        prepare()
        play()
    }

    Column {
        Column(modifier= Modifier.weight(4F)) {
            AsyncImage(
                model = media.image,
                contentDescription = media.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(basePadding))
        Column(modifier= Modifier.weight(1F)) {
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
    }
}