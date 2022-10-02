package com.example.movieappkotlin.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieappkotlin.utils.Constants
import com.example.movieappkotlin.network.MovieInterFace
import com.example.movieappkotlin.response.Movie

class MoviePaging(val s:String, val movieInterFace: MovieInterFace) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {

            val data =movieInterFace.getAllMovies(s,page,Constants.api_key)
            Log.e("moves",data.body().toString())
            LoadResult.Page(
                data=data.body()?.Search!!,
                prevKey = if(page==1)null else page-1,
                nextKey =if(data.body()?.Search?.isEmpty()!!)null else page+1

            )


        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }

    }
}