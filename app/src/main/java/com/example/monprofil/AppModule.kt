package com.example.monprofil

import android.content.Context
import androidx.room.Room
import com.example.monprofil.api.FakeTmdbApi
import com.example.monprofil.api.TmdbAPI
import com.example.monprofil.converter.Converters
import com.example.monprofil.database.AppDatabase
import com.example.monprofil.database.FilmDao
import com.example.monprofil.repository.Repository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier annotation class FakeApi
@Qualifier annotation class RealApi

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @RealApi
    @Singleton
    @Provides
    fun rpovideTmdbApi(): TmdbAPI =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TmdbAPI::class.java)

    @FakeApi
    @Singleton
    @Provides
    fun provideFakeTmdbApi() : TmdbAPI{return FakeTmdbApi()}

    @Singleton
    @Provides
    fun provideFilmDAO(@ApplicationContext context:Context): FilmDao {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database-name")
            .addTypeConverter(Converters(Moshi.Builder().build()))
            .build().filmDao()
    }

    @Singleton
    @Provides
    fun provideRepository(@RealApi api: TmdbAPI, db: FilmDao)=
        Repository(api, db)
}