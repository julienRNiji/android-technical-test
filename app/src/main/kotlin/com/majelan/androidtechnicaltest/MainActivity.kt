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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.majelan.androidtechnicaltest.features.artists.presentation.ArtistsComposable
import com.majelan.androidtechnicaltest.features.artists.presentation.ArtistsViewModel
import com.majelan.androidtechnicaltest.features.medias.presentation.MediasComposable
import com.majelan.androidtechnicaltest.ui.theme.BasicComposeAppTheme
import com.majelan.androidtechnicaltest.ui.values.Screen
import com.majelan.androidtechnicaltest.ui.values.mediasArg
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
                                    navController.navigate(Screen.Medias.getFullRoute(album))
                                }
                            )
                        }

                        composable(Screen.Medias.route, arguments = listOf(
                            navArgument(name = mediasArg) {
                                type = NavType.StringType
                            }
                        )) {
                            val album = it.arguments?.getString(mediasArg).orEmpty()
                            MediasComposable(album = album)
                        }
                    }
                }
            }
        }
    }
}