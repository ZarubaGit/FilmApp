package com.example.filmapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("/en/API/SearchMovie/k_bk23p35w/{expression}")
    fun findMovie(@Path("expression")expression: String): Call<MovieResponse>
}