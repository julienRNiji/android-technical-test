package com.majelan.androidtechnicaltest.features.artists.presentation

import com.majelan.androidtechnicaltest.common.domain.entity.ArtistEntity

sealed class UiState {
    data object Init : UiState()

    data class Data(
        val artists: List<ArtistEntity> = emptyList()
    ) : UiState()

    data object Error : UiState()
}
