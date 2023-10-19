package com.majelan.androidtechnicaltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.majelan.androidtechnicaltest.features.artists.presentation.ArtistsComposable
import com.majelan.androidtechnicaltest.features.artists.presentation.ArtistsViewModel
import com.majelan.androidtechnicaltest.ui.theme.BasicComposeAppTheme
import com.majelan.androidtechnicaltest.ui.values.artistsRoute
import com.majelan.androidtechnicaltest.ui.values.mediasRoute
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
                    NavHost(navController = navController, startDestination = artistsRoute) {
                        composable(artistsRoute) {
                            val viewModel = hiltViewModel<ArtistsViewModel>()
                            viewModel.getMusicsBis()

                            ArtistsComposable(
                                viewModel = viewModel,
                                onClickToAlbum = { data ->
                                    // TODO Pass data to retrieve which album has been clicked on
                                    navController.navigate(mediasRoute)
                                }
                            )
                        }
                        composable(mediasRoute) {
                            Text("Album Detail Screen with list of Medias displayed")
                        }
                    }
                }
            }
        }
    }
}