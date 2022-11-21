package com.example.monprofil

import android.graphics.Color
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ScreenAccueil(
    windowClass: WindowSizeClass,
    viewModel: MainViewModel,
    navHostController: NavHostController
) {
    val movies by viewModel.movies.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Films")
                        },
                navigationIcon = {IconButton(onClick = { navHostController.navigate("profile") }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }},
            actions = {
                //IconButton(onClick = {champRecherche()}) {
                    //Icon(Icons.Filled.Search, null)
               // }
            })
        },
        bottomBar = { BottomNavigationBar(navController = navHostController) }) {
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                if (movies.isEmpty()) viewModel.getFilmsInitiaux()
                LazyVerticalGrid(
                    GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(movies) { movie ->
                        val urlImage = "https://image.tmdb.org/t/p/w500" + movie.poster_path
                        Column(Modifier.clickable { viewModel.detailMovie(movie.id) }) {
                            AsyncImage(
                                model = urlImage,
                                contentDescription = movie.title
                            )
                            Text(text = movie.title)
                        }

                    }
                }
            }
            else -> {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                }
            }
        }
    }
}

@Composable
fun champRecherche(){
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    TextField(value = text, onValueChange = { newMovies -> text =newMovies}, placeholder = {Text(text= "Recherche")})
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = navItem.image, contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}