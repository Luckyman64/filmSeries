package com.example.monprofil

import TmdbResultMovie
import android.app.Person
import retrofit2.http.*

interface TmdbAPI {
    @GET("movie/{movie_id}")
    suspend fun movieDetails(@Path("movie_id") id:Int, @Query("api_key") apikey: String): TmdbResultMovie
    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key") apikey: String, @Query("query") searchTexte: String): TmdbResultMovie
    @GET("trending/movie/week")
    suspend fun getFilmsAccueil(@Query("api_key") apikey:String): TmdbResultMovie
}