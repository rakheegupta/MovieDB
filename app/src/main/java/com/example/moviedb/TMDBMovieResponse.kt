package com.example.moviedb

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class TMDBMovieResponse {

    var results = ArrayList<Movie>()

    fun parseJson(response:String):TMDBMovieResponse{
        val gson: Gson = GsonBuilder().create()
        return gson.fromJson(response,TMDBMovieResponse::class.java)
    }
    
    fun parseJSON(response: String?): TMDBMovieResponse? {
        val gson = GsonBuilder().create()
        return gson.fromJson(
            response,
            TMDBMovieResponse::class.java
        )
    }
}