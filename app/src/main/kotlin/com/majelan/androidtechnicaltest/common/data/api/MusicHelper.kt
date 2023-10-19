package com.majelan.androidtechnicaltest.common.data.api

import com.majelan.androidtechnicaltest.common.domain.entity.MusicResponse
import retrofit2.Response

interface MusicHelper {
    suspend fun getMusics(): Response<MusicResponse>
}