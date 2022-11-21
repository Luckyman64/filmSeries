//package com.example.monprofil
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Singleton
//    @Provides
//    fun rpovideTmdbApi(): TmdbAPI =
//        Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/3/")
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//            .create(TmdbAPI::class.java)
//
//    @Singleton
//    @Provides
//    fun provideRepository(api: TmdbAPI, db: FilmDao)=
//        Repository(api, db)
//}