package com.majelan.androidtechnicaltest.ui.values

// Routes
private const val artistsRoute = "artists"
private const val mediasRoute = "medias"

// Args
const val mediasArg = "album"

sealed class Screen(val route: String) {
    data object Artists : Screen(route = artistsRoute)
    data object Medias : Screen(route = "$mediasRoute/{$mediasArg}") {
        fun getFullRoute(album: String): String {
            return "$mediasRoute/$album"
        }
    }
}