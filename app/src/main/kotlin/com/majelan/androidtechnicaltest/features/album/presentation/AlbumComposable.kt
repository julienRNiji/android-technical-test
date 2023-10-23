package com.majelan.androidtechnicaltest.features.album.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import coil.compose.AsyncImage
import com.majelan.androidtechnicaltest.common.domain.entity.AlbumEntity
import com.majelan.androidtechnicaltest.common.domain.entity.MediaEntity
import com.majelan.androidtechnicaltest.common.presentation.Error
import com.majelan.androidtechnicaltest.ui.values.albumImageSize
import com.majelan.androidtechnicaltest.ui.values.artistImageSize
import com.majelan.androidtechnicaltest.ui.values.basePadding
import com.majelan.androidtechnicaltest.ui.values.bigPadding
import com.majelan.androidtechnicaltest.ui.values.titleFontSize

@Composable
fun AlbumComposable(album: AlbumEntity?, onClickToMedia: (media: MediaEntity) -> Unit) {
    if (album != null) {
        AlbumComposition(album, onClickToMedia)
    } else {
        Error()
    }
}

@Composable
fun AlbumComposition(album: AlbumEntity, onClickToMedia: (media: MediaEntity) -> Unit) {
    Column {
        AsyncImage(
            model = album.image,
            contentDescription = album.name,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .height(albumImageSize)
        )
        Spacer(modifier = Modifier.height(basePadding))
        Text(
            text = album.name,
            style = TextStyle(fontSize = titleFontSize),
            modifier = Modifier.padding(start = basePadding)
        )
        Text(
            text = album.artist,
            modifier = Modifier.padding(start = basePadding),
        )
        Spacer(modifier = Modifier.height(basePadding))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Play Album")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(basePadding),
        ) {
            items(album.medias) { item ->
                MediaItem(item, onClickToMedia)
            }
        }
    }
}

@Composable
fun MediaItem(mediaEntity: MediaEntity, onClickToMedia: (media: MediaEntity) -> Unit) {
    Column(modifier = Modifier.clickable { onClickToMedia.invoke(mediaEntity) }) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = mediaEntity.image,
                contentDescription = mediaEntity.title,
                modifier = Modifier
                    .width(artistImageSize)
                    .height(artistImageSize)
            )
            Spacer(modifier = Modifier.width(basePadding))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = mediaEntity.title,
                    style = TextStyle(fontSize = titleFontSize)
                )
                Text(text = getTrackNumber(mediaEntity = mediaEntity))
            }
        }
        Spacer(modifier = Modifier.height(bigPadding))
    }
}

private fun getTrackNumber(mediaEntity: MediaEntity): String {
    return "${mediaEntity.trackNumber}/${mediaEntity.totalTrackCount}"
}