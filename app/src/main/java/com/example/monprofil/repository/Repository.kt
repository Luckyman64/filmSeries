package com.example.monprofil.repository

import com.example.monprofil.api.TmdbAPI
import com.example.monprofil.database.FilmDao
import com.example.monprofil.entity.ActeurEntity
import com.example.monprofil.entity.FilmEntity
import com.example.monprofil.entity.SerieEntity
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class Repository(val api: TmdbAPI, val db: FilmDao) {
    suspend fun lastmovies(apiKey: String) = api.lastmovies(apiKey)
    suspend fun searchmovies(apiKey: String, searchText: String) = api.searchmovies(apiKey, searchText)
    suspend fun searchSeries(apiKey: String, searchText: String) = api.searchSeries(apiKey, searchText)
    suspend fun searchActors(apiKey: String, searchText: String) = api.searchActors(apiKey, searchText)
    suspend fun lastTv(apiKey: String) = api.lastTv(apiKey)
    suspend fun lastPerson(apiKey: String) = api.lastPerson(apiKey)
    suspend fun movieDetails(apiKey: String, idMovie: String, appendToResponse: String) = api.movieDetails(apiKey, idMovie, appendToResponse)
    suspend fun serieDetails(apiKey: String, idSerie: String, appendToResponse: String) = api.serieDetails(apiKey, idSerie, appendToResponse)

    suspend fun getFavFilms() = db.getFavFilms()
    suspend fun getFavSeries() = db.getFavSeries()
    suspend fun getFavActeurs() = db.getFavActeurs()
    suspend fun deleteFilm(idMovie: String) = db.deleteFilm(idMovie)
    suspend fun deleteSerie(idSerie: String) = db.deleteSerie(idSerie)
    suspend fun deleteActeur(idActor: String) = db.deleteActeur(idActor)
    suspend fun insertFilm(movie: FilmEntity) = db.insertFilm(movie)
    suspend fun insertSerie(serie: SerieEntity) = db.insertSerie(serie)
    suspend fun insertActor(actor: ActeurEntity) = db.insertActor(actor)
}
