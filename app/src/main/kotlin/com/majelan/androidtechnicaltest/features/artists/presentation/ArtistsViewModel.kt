package com.majelan.androidtechnicaltest.features.artists.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.majelan.androidtechnicaltest.common.domain.entity.ArtistEntity
import com.majelan.androidtechnicaltest.common.domain.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val mainRepository: MusicRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<UiState>(UiState.Init)

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun getMusics() = viewModelScope.launch {
        mainRepository.getMusics().let { response ->
            if (response.isSuccessful) {
                // FIXME Extract all business logic in UC and do TU
                val albums: MutableList<String> = mutableListOf()
                response.body()?.music?.forEach { media ->
                    val existingAlbum = albums.find { album -> album == media.album }
                    if (existingAlbum == null) {
                        albums.add(media.album)
                    }
                }

                val artistsEntities: MutableList<ArtistEntity> = mutableListOf()
                albums.forEach { album ->
                    val medias = response.body()?.music?.filter { it.album == album }?.toList()
                    medias?.let {
                        artistsEntities.add(
                            ArtistEntity(
                                album = it[0].album,
                                name = it[0].artist,
                                image = it[0].image,
                                medias = it
                            )
                        )
                    }

                }
                if (artistsEntities.isNotEmpty()) {
                    _uiState.value = UiState.Data(artistsEntities)
                } else {
                    _uiState.value = UiState.Error
                }
            } else {
                _uiState.value = UiState.Error
            }
        }
    }
}