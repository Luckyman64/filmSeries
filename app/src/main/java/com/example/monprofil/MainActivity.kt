package com.example.monprofil

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
                startDestination = "profile"
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
                        navController
                    )
                }
                //composable("Film") {
                    //ScreenFilm(windowClass = windowSizeClass, viewModel = viewmodel, navController, id)
                //}
            }
        }
        //films(name = "Hocus", viewmodel)
        //Text("yop")
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

