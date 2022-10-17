package com.example.monprofil

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun films(name: String, viewModel: MainViewModel) {
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) viewModel.searchMovie(name)

    LazyColumn{
        items(movies){
                movie -> Text(text = movie.original_title)
        }
    }
}