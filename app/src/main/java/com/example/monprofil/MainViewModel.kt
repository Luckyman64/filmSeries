package com.example.monprofil

import Movie
import TmdbResult
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())

    val apikey = "c15a319ea46106ff5e1547059a870c14"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun searchMovie(motcle: String) {
        viewModelScope.launch {
            //movies.value = service.getFilmsParMotCle(apikey, motcle).results

            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<TmdbResult> = moshi.adapter(TmdbResult::class.java)

            val result = jsonAdapter.fromJson(JsonResult)
            if (result != null) movies.value = result.results
        }
    }
}
