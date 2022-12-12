package com.example.monprofil

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.monprofil.viewmodels.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenSeries(
    windowClass: WindowSizeClass,
    viewmodel: MainViewModel,
    navController: NavController
) {
    val series by viewmodel.series.collectAsState()
    var isSearch by remember {
        mutableStateOf(false)
    }
    val searchWidgetState by viewmodel.searchWidgetState
    val searchTextState by viewmodel.searchTextState
    viewmodel.getSeriesInitiaux()
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    MainAppBar(
                        searchWidgetState = searchWidgetState,
                        searchTextState = searchTextState,
                        type = "une série",
                        onTextChange = {
                            viewmodel.updateSearchTextState(newValue = it)
                        },
                        onCloseClicked = {
                            viewmodel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                        },
                        onFavoriteClicked = {navController.navigate("profile")},
                        onSearchClicked = {
                            Log.d("Searched Text", it)
                            viewmodel.getSearchSeries()
                        },
                        onSearchTriggered = {
                            viewmodel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                        }
                    )
                },
                bottomBar = {
                    BottomNavigation(backgroundColor = colorResource(R.color.purple_700)) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        val items = listOf(
                            Screen(
                                "films",
                                painterResource(id = R.drawable.movies),
                                "Icone Films",
                                "Films"
                            ),
                            Screen(
                                "series",
                                painterResource(id = R.drawable.ic_series),
                                "Icone Series",
                                "Séries"
                            ),
                            Screen(
                                "actors",
                                painterResource(id = R.drawable.actors),
                                "Icone Acteurs",
                                "Acteurs"
                            )
                        )
                        items.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        screen.resourceId,
                                        contentDescription = screen.description
                                    )
                                },
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
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(series) { serie ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(20.dp)
                                .background(Color.White)
                                .padding(10.dp)
                                .clickable { navController.navigate("detailsSerie/${serie.id}") },
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500" + serie.poster_path,
                                contentDescription = "Affiche de la série"
                            )
                            Text(text = serie.name)
                            Text(text = serie.first_air_date)
                        }
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



