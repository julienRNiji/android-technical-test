package com.majelan.androidtechnicaltest.features.album.navigation

import com.google.gson.Gson
import com.majelan.androidtechnicaltest.common.domain.entity.AlbumEntity
import com.majelan.androidtechnicaltest.common.navigation.JsonNavType

class AlbumArgType : JsonNavType<AlbumEntity>() {
    override fun fromJsonParse(value: String): AlbumEntity =
        Gson().fromJson(value, AlbumEntity::class.java)

    override fun AlbumEntity.getJsonParse(): String = Gson().toJson(this)
}