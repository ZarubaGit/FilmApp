package com.example.filmapp

data class MovieResponse(
    val searchType: String,
    val expression: String,
    val results: List<Movie>
)
