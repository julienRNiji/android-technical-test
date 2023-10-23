package com.majelan.androidtechnicaltest.features.player.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.majelan.androidtechnicaltest.common.extension.toMinSecFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val defaultDuration = "00:00"

@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA PRIVATE
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var player: ExoPlayer

    private var _playerState = MutableStateFlow<PlayerState>(PlayerState.Init)

    private var _timeState = MutableStateFlow(TimeState(defaultDuration, defaultDuration))

    ///////////////////////////////////////////////////////////////////////////
    // DATA PUBLIC
    ///////////////////////////////////////////////////////////////////////////

    val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()

    val timeState: StateFlow<TimeState> = _timeState.asStateFlow()

    ///////////////////////////////////////////////////////////////////////////
    // OVERRIDE
    ///////////////////////////////////////////////////////////////////////////

    override fun onCleared() {
        player.release()
        super.onCleared()
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun setMedia(mediaItem: MediaItem, context: Context) {
        player = ExoPlayer.Builder(context).build().apply {
            addMediaItem(mediaItem)
            volume = 0.5f
            prepare()
        }
        startTimeFlux()
        _playerState.value = PlayerState.Ready
    }

    fun playMedia() {
        player.play()
        _playerState.value = PlayerState.Play
    }

    fun pauseMedia() {
        player.pause()
        _playerState.value = PlayerState.Pause
    }

    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE
    ///////////////////////////////////////////////////////////////////////////

    private fun startTimeFlux() {
        viewModelScope.launch {
            flow {
                while (true) {
                    val timePlayed = player.currentPosition
                    emit(timePlayed)
                    delay(200)
                }
            }.map {
                it.toMinSecFormat()
            }.collect { timePlayed ->
                val duration = player.duration.toMinSecFormat()
                _timeState.value = TimeState(timePlayed, duration)
            }
        }
    }
}