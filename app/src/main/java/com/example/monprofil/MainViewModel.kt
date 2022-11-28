package com.example.monprofil

import Actors
import Movie
import Series
import TmdbResultActor
import TmdbResultMovie
import TmdbResultSerie
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainViewModel : ViewModel() {
    var movies = MutableStateFlow<List<Movie>>(listOf())
    var serie = MutableStateFlow<List<Series>>(listOf())
    var actors = MutableStateFlow<List<Actors>>(listOf())

    val apikey = "c15a319ea46106ff5e1547059a870c14"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun getFilmsInitiaux(){
        viewModelScope.launch {
            //movies.value = service.getFilmsAccueil(apikey).results

            val moshi : Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<TmdbResultMovie> = moshi.adapter(TmdbResultMovie::class.java)
            //val result = jsonAdapter.fromJson(movies.toString())
            val result = jsonAdapter.fromJson(JsonResult)
            if(result != null) movies.value = result.results
        }
    }


    fun searchMovie(motcle: String) {
        viewModelScope.launch {
            //movies.value = service.getFilmsParMotCle(apikey, motcle).results

            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<TmdbResultMovie> = moshi.adapter(TmdbResultMovie::class.java)

            //val result = jsonAdapter.fromJson(movies.toString())
            val result = jsonAdapter.fromJson(JsonResult)
            if (result != null) movies.value = result.results
        }
    }

    fun detailMovie(id: Int){
        viewModelScope.launch {
            //movies.value = service.movieDetails(id).results

            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<TmdbResultMovie> = moshi.adapter(TmdbResultMovie::class.java)

            val result = jsonAdapter.fromJson(JsonResult)
            if (result != null) movies.value = result.results
        }
    }
    fun getSerieInitiaux(){
        viewModelScope.launch {
            //serie.value = service.getFilmsAccueil(apikey).results

            val moshi : Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<TmdbResultSerie> = moshi.adapter(TmdbResultSerie::class.java)
            //val result = jsonAdapter.fromJson(movies.toString())
            val result = jsonAdapter.fromJson(JsonResultSerie)
            if(result != null) serie.value = result.results
        }
    }
    fun getActorInitiaux(){
        viewModelScope.launch {
            //actors.value = service.getFilmsAccueil(apikey).results

            val moshi : Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<TmdbResultActor> = moshi.adapter(TmdbResultActor::class.java)
            //val result = jsonAdapter.fromJson(movies.toString())
            val result = jsonAdapter.fromJson(JsonResultActor)
            if(result != null) actors.value = result.results
        }
    }
}
