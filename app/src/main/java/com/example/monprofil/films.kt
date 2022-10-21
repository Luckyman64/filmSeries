package com.example.monprofil

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun films(name: String, viewModel: MainViewModel) {
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) viewModel.searchMovie(name)

    LazyVerticalGrid(cells = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)){
        items(movies){
                movie -> Text(text = movie.original_title)

        }
    }
}