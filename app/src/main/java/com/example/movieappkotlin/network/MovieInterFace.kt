package com.example.movieappkotlin.network

import com.example.movieappkotlin.response.MovieResponce
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterFace {

    @GET("/")
    suspend fun getAllMovies(
        @Query ("apikey") apikey: String,
        @Query("s")s:String,
        @Query ("page")page:Int
    ):Response<MovieResponce>



}