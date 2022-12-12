package com.example.monprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monprofil.ui.theme.MonProfilTheme
import com.example.monprofil.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonProfilTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                val navController = rememberNavController()
                val viewmodel: MainViewModel by viewModels()
                NavHost(navController = navController, startDestination = "profile") {
                    composable("profile") { Screen(windowSizeClass, navController) }
                    composable("films") { Films(windowSizeClass, viewmodel, navController) }
                    composable("series") { ScreenSeries(windowSizeClass, viewmodel, navController) }
                    composable("actors") { ScreenActor(windowSizeClass, viewmodel, navController) }
                    composable("detailsFilm/{idFilm}") { backStackEntry -> ScreenMovie(windowSizeClass, viewmodel, backStackEntry.arguments?.getString("idFilm")) }
                    composable("detailsSerie/{idSerie}") { backStackEntry -> ScreenSerie(windowSizeClass, viewmodel, backStackEntry.arguments?.getString("idSerie")) }
                    composable("favorites"){ ScreenFavorite(windowSizeClass,viewmodel,navController)}
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MonProfilTheme {
        Screen()
    }
}*/