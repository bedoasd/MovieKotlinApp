package com.example.movieappkotlin.ui

import android.util.Log
import com.example.movieappkotlin.moviedetails.MovieDetails
import com.example.movieappkotlin.network.MovieInterFace
import com.example.movieappkotlin.utils.Constants
import com.example.movieappkotlin.utils.Result

class MovieDetailsRepository(private val movieInterFace: MovieInterFace) {

    suspend fun getMovieDetails(imdb:String):Result<MovieDetails>{
        return try {

            val response=movieInterFace.getMoviesDetails(Constants.api_key,imdb)
            Log.e("dets",response.body().toString())

            if (response.isSuccessful){

                Result(Result.Status.SUCCESS, response.body()!! ,null)


            }
            else
            {
                Result(Result.Status.ERROR,null,null)
            }
        }catch (e:Exception){
            Result(Result.Status.ERROR,null,null)
    }
}
}