package com.majelan.androidtechnicaltest.features.artists.presentation

import com.majelan.androidtechnicaltest.common.domain.entity.AlbumEntity

sealed class UiState {
    data object Init : UiState()

    data class Data(
        val artists: List<AlbumEntity> = emptyList()
    ) : UiState()

    data object Error : UiState()
}
