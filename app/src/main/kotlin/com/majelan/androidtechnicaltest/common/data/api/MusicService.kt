package com.majelan.androidtechnicaltest.common.data.api

import com.majelan.androidtechnicaltest.common.domain.entity.MusicResponse
import retrofit2.Response
import retrofit2.http.GET

interface MusicService {

    @GET("catalog.json")
    suspend fun getMusics(): Response<MusicResponse>
}