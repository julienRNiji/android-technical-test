package com.majelan.androidtechnicaltest.features.artists.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import coil.compose.AsyncImage
import com.majelan.androidtechnicaltest.common.domain.entity.AlbumEntity
import com.majelan.androidtechnicaltest.ui.values.artistImageSize
import com.majelan.androidtechnicaltest.ui.values.basePadding
import com.majelan.androidtechnicaltest.ui.values.bigPadding
import com.majelan.androidtechnicaltest.ui.values.titleFontSize

@Composable
fun ArtistsComposable(
    viewModel: ArtistsViewModel,
    onClickToAlbum: (album: AlbumEntity) -> Unit
) {
    val uiState: UiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.Error -> Text(
            text = "Error while loading",
            style = TextStyle(
                fontSize = titleFontSize
            )
        )

        UiState.Init -> Text(
            text = "Loading ...",
            style = TextStyle(
                fontSize = titleFontSize
            )
        )

        is UiState.Data -> ArtistList(
            state = uiState as UiState.Data,
            onClickToAlbum = onClickToAlbum
        )
    }
}

@Composable
fun ArtistList(state: UiState.Data, onClickToAlbum: (album: AlbumEntity) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(basePadding),
    ) {
        items((state).artists) { item ->
            ArtistItem(item, onClickToAlbum)
        }
    }
}

@Composable
fun ArtistItem(item: AlbumEntity, onClickToAlbum: (album: AlbumEntity) -> Unit) {
    Column(modifier = Modifier.clickable { onClickToAlbum.invoke(item) }) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = item.image,
                contentDescription = item.name,
                modifier = Modifier
                    .width(artistImageSize)
                    .height(artistImageSize)
            )
            Spacer(modifier = Modifier.width(basePadding))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = item.name,
                    style = TextStyle(fontSize = titleFontSize)
                )
                Text(text = item.artist)
            }
        }
        Spacer(modifier = Modifier.height(bigPadding))
    }
}