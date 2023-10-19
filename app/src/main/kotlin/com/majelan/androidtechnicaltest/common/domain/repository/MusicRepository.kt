package com.majelan.androidtechnicaltest.common.domain.repository

import com.majelan.androidtechnicaltest.common.data.api.MusicHelper
import javax.inject.Inject

class MusicRepository @Inject constructor(private val musicHelper: MusicHelper) {

    suspend fun getMusics() = musicHelper.getMusics()
}