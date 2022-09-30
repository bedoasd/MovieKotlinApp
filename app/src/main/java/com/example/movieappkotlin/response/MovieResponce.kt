package com.example.movieappkotlin.response

data class MovieResponce(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)