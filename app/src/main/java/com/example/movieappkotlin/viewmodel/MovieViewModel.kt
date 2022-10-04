package com.example.movieappkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.movieappkotlin.moviedetails.MovieDetails
import com.example.movieappkotlin.network.MovieInterFace
import com.example.movieappkotlin.paging.MoviePaging
import com.example.movieappkotlin.ui.MovieDetailsRepository
import com.example.movieappkotlin.utils.Events
import com.example.movieappkotlin.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel  @Inject constructor(private val movieInterFace: MovieInterFace,private val movieDetailsRepository: MovieDetailsRepository) :ViewModel(){

    private val query=MutableLiveData<String>()


    val list=query.switchMap {query->
        Pager(PagingConfig(pageSize = 10)){
            MoviePaging(query,movieInterFace)
        }.liveData.cachedIn(viewModelScope)




    }
    fun setQuery(s :String){
        query.postValue(s)

    }

    private val _movieDetails= MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails:LiveData <Events <Result < MovieDetails >>> = _movieDetails

     fun getMovieDetails(imdb:String)=viewModelScope.launch{

         _movieDetails.postValue(Events(Result(Result.Status.LOADING, null,null)))
         _movieDetails.postValue(Events(movieDetailsRepository.getMovieDetails(imdb)))


     }



}