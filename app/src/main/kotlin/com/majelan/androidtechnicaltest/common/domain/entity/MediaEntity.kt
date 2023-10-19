package com.majelan.androidtechnicaltest.common.domain.entity

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
)

data class ArtistEntity(
    val name: String,
    val album: String,
    val image: String,
    val medias: List<MediaEntity>
)

data class MusicResponse(
    val music: List<MediaEntity>
)