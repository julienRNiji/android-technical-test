package com.majelan.androidtechnicaltest.common.extension

fun Long.toMinSecFormat(): String {
    val min = this / 1000 / 60
    val second = this / 1000 % 60
    return "${String.format("%02d", min)}:${String.format("%02d", second)}"
}