package com.majelan.androidtechnicaltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.majelan.androidtechnicaltest.common.domain.entity.AlbumEntity
import com.majelan.androidtechnicaltest.common.domain.entity.MediaEntity
import com.majelan.androidtechnicaltest.features.artists.presentation.ArtistsComposable
import com.majelan.androidtechnicaltest.features.artists.presentation.ArtistsViewModel
import com.majelan.androidtechnicaltest.features.album.navigation.AlbumArgType
import com.majelan.androidtechnicaltest.features.album.presentation.AlbumComposable
import com.majelan.androidtechnicaltest.features.player.navigation.MediaArgType
import com.majelan.androidtechnicaltest.features.player.presentation.PlayerComposable
import com.majelan.androidtechnicaltest.features.player.presentation.PlayerViewModel
import com.majelan.androidtechnicaltest.ui.theme.BasicComposeAppTheme
import com.majelan.androidtechnicaltest.ui.values.Screen
import com.majelan.androidtechnicaltest.ui.values.albumArg
import com.majelan.androidtechnicaltest.ui.values.playerArg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()

            BasicComposeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Artists.route
                    ) {
                        composable(Screen.Artists.route) {
                            val viewModel = hiltViewModel<ArtistsViewModel>()
                            viewModel.getMusics()

                            ArtistsComposable(
                                viewModel = viewModel,
                                onClickToAlbum = { album ->
                                    navController.navigate(Screen.Album.getFullRoute(album.toString()))
                                }
                            )
                        }

                        composable(Screen.Album.route, arguments = listOf(
                            navArgument(name = albumArg) {
                                type = AlbumArgType()
                            }
                        )) {
                            val album = it.arguments?.getString(albumArg)
                                ?.let { json -> Gson().fromJson(json, AlbumEntity::class.java) }
                            AlbumComposable(
                                album = album,
                                onClickToMedia = { media ->
                                    navController.navigate(Screen.Player.getFullRoute(media.toString()))
                                }
                            )
                        }

                        composable(Screen.Player.route, arguments = listOf(
                            navArgument(name = playerArg) {
                                type = MediaArgType()
                            }
                        )) {
                            val playerViewModel = hiltViewModel<PlayerViewModel>()
                            val media = it.arguments?.getString(playerArg)
                                ?.let { json -> Gson().fromJson(json, MediaEntity::class.java) }
                            PlayerComposable(mediaEntity = media, viewModel = playerViewModel)
                        }
                    }
                }
            }
        }
    }
}