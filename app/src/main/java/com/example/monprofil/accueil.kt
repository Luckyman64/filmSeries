package com.example.monprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import java.net.URL

@Composable
fun ScreenAccueil(windowClass: WindowSizeClass, viewModel: MainViewModel) {
    val movies by viewModel.movies.collectAsState()
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (movies.isEmpty()) viewModel.getFilmsInitiaux()
            LazyColumn{
                items(movies){
                    movie ->
                    val urlImage = "https://image.tmdb.org/t/p/w500" + movie.poster_path
                    AsyncImage(
                        model = urlImage,
                        contentDescription = movie.title
                    )
                    Text(text = movie.title)
                }
            }
        }
        else -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically)
            {

            }
        }
    }
}