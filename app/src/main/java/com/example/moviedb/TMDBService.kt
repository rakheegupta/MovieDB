package com.example.moviedb

import retrofit2.Call
import retrofit2.http.GET

interface TMDBService {
    @GET("/movie/550")
    fun listMovies(): Call<TMDBMovieResponse>


}