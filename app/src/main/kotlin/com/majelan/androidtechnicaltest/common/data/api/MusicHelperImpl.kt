package com.majelan.androidtechnicaltest.common.data.api

import com.majelan.androidtechnicaltest.common.domain.entity.MusicResponse
import retrofit2.Response
import javax.inject.Inject

class MusicHelperImpl @Inject constructor(
    private val musicService: MusicService
) : MusicHelper {

    override suspend fun getMusics(): Response<MusicResponse> = musicService.getMusics()
}