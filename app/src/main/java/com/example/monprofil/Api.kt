package com.example.monprofil

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class api {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service = retrofit.create(TmdbAPI::class.java)
}