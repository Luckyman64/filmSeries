//package com.example.monprofil
//
//import android.graphics.Color
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.GridCells
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyVerticalGrid
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.AppBarDefaults
//import androidx.compose.material.BottomNavigation
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material3.*
//import androidx.compose.material3.windowsizeclass.WindowSizeClass
//import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color.Companion.Blue
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.NavGraph.Companion.findStartDestination
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.currentBackStackEntryAsState
//import coil.compose.AsyncImage
//
//@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
//@Composable
//fun ScreenFilm(
//    windowClass: WindowSizeClass,
//    viewModel: MainViewModel,
//    navHostController: NavHostController,
//    id: Int
//) {
//    val movies by viewModel.movies.collectAsState()
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController = navHostController) }) {
//        when (windowClass.widthSizeClass) {
//            WindowWidthSizeClass.Compact -> {
//                Column() {
//                    val urlImage = "https://image.tmdb.org/t/p/w500" + movie.poster_path
//                         AsyncImage(
//                            model = urlImage,
//                            contentDescription = movie.title
//                        )
//                        Text(text = movie.title)
//                    }
//
//                }
//            }
//        }
//        else -> {
//        Row(
//            modifier = Modifier.fillMaxSize(),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        )
//        {
//
//        }
//    }
//    }
//}
//}

