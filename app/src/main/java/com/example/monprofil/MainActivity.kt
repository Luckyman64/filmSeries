package com.example.monprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monprofil.ui.theme.MonProfilTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel : MainViewModel by viewModels()
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
                composable("FilmsList") { ScreenAccueil(windowClass = windowSizeClass, viewmodel) }
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




