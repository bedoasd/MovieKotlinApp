package com.example.movieappkotlin.hilt

import com.example.movieappkotlin.utils.Constants
import com.example.movieappkotlin.network.MovieInterFace
import com.example.movieappkotlin.response.Movie
import com.example.movieappkotlin.ui.MovieDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    fun provideRetrofitIntersface():MovieInterFace{

        return Retrofit.Builder().baseUrl(Constants.BASE_URl).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(MovieInterFace::class.java)
    }

    @Provides
    fun provideRepository(movieInterFace: MovieInterFace):MovieDetailsRepository{
        return MovieDetailsRepository(movieInterFace)
    }

}