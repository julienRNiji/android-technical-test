package com.majelan.androidtechnicaltest.features.player.navigation

import com.google.gson.Gson
import com.majelan.androidtechnicaltest.common.domain.entity.MediaEntity
import com.majelan.androidtechnicaltest.common.navigation.JsonNavType

class MediaArgType : JsonNavType<MediaEntity>() {
    override fun fromJsonParse(value: String): MediaEntity =
        Gson().fromJson(value, MediaEntity::class.java)

    override fun MediaEntity.getJsonParse(): String = Gson().toJson(this)
}