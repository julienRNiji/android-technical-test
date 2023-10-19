package com.majelan.androidtechnicaltest.ui.values

// Routes
private const val artistsRoute = "artists"
private const val albumRoute = "album"
private const val playerRoute = "player"

// Args
const val albumArg = "name"
const val playerArg = "song"

sealed class Screen(val route: String) {
    data object Artists : Screen(route = artistsRoute)
    data object Album : Screen(route = "$albumRoute/{$albumArg}") {
        fun getFullRoute(album: String): String {
            return "$albumRoute/$album"
        }
    }

    data object Player : Screen(route = "$playerRoute/{$playerArg}") {
        fun getFullRoute(song: String): String {
            return "$playerRoute/$song"
        }
    }
}