package com.example.movieappkotlin.response

data class MovieResponce(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)