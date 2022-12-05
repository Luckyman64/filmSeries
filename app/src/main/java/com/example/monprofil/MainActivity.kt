package com.example.monprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monprofil.ui.theme.MonProfilTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel: MainViewModel by viewModels()
        setContent {
            val navController: NavHostController = rememberNavController()
            val startDestination: String = "profile"
            val windowSizeClass = calculateWindowSizeClass(this)
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable("profile") {
                    Screen(
                        windowClass = windowSizeClass,
                        onNavigateToFriends = { navController.navigate("FilmsList") })
                }
                composable("FilmsList") {
                    ScreenAccueil(
                        windowClass = windowSizeClass,
                        viewmodel,
                        navController,
                        onNavigateToFilm = { navController.navigate("FilmDetail/436270")}
                    )
                }
                composable("FilmDetail/{id}"){
                    backStackEntry ->
                    ScreenMovie(
                        windowClass = windowSizeClass,
                        viewModel = viewmodel,
                        navHostController = navController,
                        backStackEntry.arguments?.getInt("id")
                    )
                }
                composable("searchFilm/{motcle}"){
                    backStackEntry ->
                    SearchResult(
                        windowClass = windowSizeClass,
                        viewmodel,
                        navController,
                        backStackEntry.arguments?.getString("motcle")
                    )
                }
                composable("Serie"){
                    ScreenSerie(
                        windowClass = windowSizeClass,
                        viewmodel ,
                        navController)
                }
                composable("Actors"){
                    ScreenActor(windowClass = windowSizeClass,
                        viewmodel ,
                        navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "$name")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MonProfilTheme {
        Greeting("Android")
    }
}

@Composable
fun Films() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "films",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Series() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = "series",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Acteurs() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "acteurs",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
    }
}

