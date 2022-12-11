package com.example.monprofil

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.monprofil.viewmodels.MainViewModel


@Composable
fun SearchResult(
    windowClass: WindowSizeClass,
    viewModel: MainViewModel,
    navController: NavController
) {
    val movies by viewModel.movies.collectAsState()
    var isSearch by remember {
        mutableStateOf(false)
    }
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Films")
                },
                navigationIcon = {IconButton(onClick = { navController.navigate("profile") }) {
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
        bottomBar = {
            BottomNavigation ( backgroundColor = colorResource(R.color.purple_700) ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val items = listOf(
                    Screen("films", painterResource(id = R.drawable.movies), "Icone Films", "Films") ,
                    Screen("series", painterResource(id = R.drawable.ic_series), "Icone Series", "Séries"),
                    Screen("actors", painterResource(id = R.drawable.actors), "Icone Acteurs", "Acteurs")
                )
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.resourceId, contentDescription = screen.description) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ){
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            modifier = Modifier.background(Color.Black),) {
            items(movies) { film ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(20.dp)
                        .background(Color.White)
                        .padding(10.dp)
                        .clickable { navController.navigate("detailsFilm/${film.id}") },
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500" + film.poster_path,
                        contentDescription = "Affiche du film"
                    )
                    Text(text = film.title)
                    Text(text = film.release_date)
                }
            }
        }
            }}
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