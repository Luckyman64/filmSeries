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
fun ScreenSerie(
    windowClass: WindowSizeClass,
    viewModel: MainViewModel,
    navHostController: NavHostController
) {
    val serie by viewModel.serie.collectAsState()
    var isSearch by remember {
        mutableStateOf(false)
    }
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
                    if (isSearch==false) {
                        IconButton(onClick = { isSearch = true }) {
                            Icon(Icons.Filled.Search, null)
                        }
                    }else{
                        var text by remember {
                            mutableStateOf(mutableStateOf(TextFieldValue("")))
                        }
                        SearchView(state = text, viewModel)
                    }
                })

        },
        bottomBar = { BottomNavigationBar(navController = navHostController) }) {
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                if (serie.isEmpty()) viewModel.getSerieInitiaux()
                LazyVerticalGrid(
                    GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(serie) { movie ->
                        val urlImage = "https://image.tmdb.org/t/p/w500" + movie.poster_path
                        Column(Modifier.clickable { viewModel.detailMovie(movie.id) }) {
                            AsyncImage(
                                model = urlImage,
                                contentDescription = movie.name
                            )
                            Text(text = movie.name)
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


