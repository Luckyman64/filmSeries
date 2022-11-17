package com.example.monprofil

import TmdbResult
import android.app.Person
import retrofit2.http.*

interface TmdbAPI {
    @GET("movie/{movie_id}")
    suspend fun movieDetails(@Path("movie_id") id:Int): TmdbResult
    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key") apikey: String, @Query("query") searchTexte: String): TmdbResult
    @GET("trending/movie/week")
    suspend fun getFilmsAccueil(@Query("api_key") apikey:String): TmdbResult
}