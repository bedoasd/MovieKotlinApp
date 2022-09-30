package com.example.movieappkotlin.hilt

import com.example.movieappkotlin.utils.Constants
import com.example.movieappkotlin.network.MovieInterFace
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(Singleton::class)
@Module
object HiltModules {

    @Provides
    fun provideRetrofitIntersface():MovieInterFace{

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URl)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            .create(MovieInterFace::class.java)


    }

}