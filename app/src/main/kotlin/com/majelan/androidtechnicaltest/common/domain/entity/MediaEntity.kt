package com.majelan.androidtechnicaltest.common.domain.entity

import android.net.Uri
import com.google.gson.Gson

// TODO could handle nullable data
data class MediaEntity(
    val id: String,
    val title: String,
    val album : String,
    val artist: String,
    val genre : String,
    val source: String,
    val image: String,
    val trackNumber: Int,
    val totalTrackCount: Int,
    val duration: Int,
    val site: String
) {

    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

data class AlbumEntity(
    val artist: String,
    val name: String,
    val image: String,
    val medias: List<MediaEntity>
) {

    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

data class MusicResponse(
    val music: List<MediaEntity>
)