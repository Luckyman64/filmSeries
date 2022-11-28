package com.example.monprofil

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavRoutes(val route: String){
    object Films: NavRoutes("films")
    object Series: NavRoutes("series")
    object Acteur: NavRoutes("acteur")
}

val items= listOf(
    NavRoutes.Films,
    NavRoutes.Series,
    NavRoutes.Acteur
)
data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

object NavBarItems{
    val BarItems = listOf(
        BarItem(
            title = "Films",
            image = Icons.Filled.Home,
            route = "FilmsList"
        ),
        BarItem(
            title = "Series",
            image = Icons.Filled.PlayArrow,
            route = "Serie"
        ),
        BarItem(
            title = "Acteur",
            image = Icons.Filled.Face,
            route = "Actors"
        )
    )
}