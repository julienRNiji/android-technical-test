package com.majelan.androidtechnicaltest.features.player.presentation

sealed class PlayerState {
    data object Init : PlayerState()

    data object Ready : PlayerState()

    data object Play : PlayerState()

    data object Pause : PlayerState()

    data object Error : PlayerState()
}