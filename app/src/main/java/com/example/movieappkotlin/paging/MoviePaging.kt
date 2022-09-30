package com.example.movieappkotlin.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieappkotlin.utils.Constants
import com.example.movieappkotlin.network.MovieInterFace
import com.example.movieappkotlin.response.Movie

class MoviePaging(val movieInterFace: MovieInterFace) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val data =movieInterFace.getAllMovies(Constants.api_key,page)
            LoadResult.Page(
                data=data.body()?.results!!,
                prevKey = if(page==1)null else page-1,
                nextKey =if(data.body()?.results?.isEmpty()!!)null else page+1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}