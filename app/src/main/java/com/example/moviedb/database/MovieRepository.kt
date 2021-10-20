package com.example.moviedb.database

import androidx.lifecycle.LiveData
import com.example.moviedb.model.Movie

class MovieRepository(private val mMovieDao: MovieDao?) {

    private var favoriteMovies: LiveData<List<Movie>>? = mMovieDao?.getAllFavorites()

    fun getFavoriteMovies(): LiveData<List<Movie>>?{
        return favoriteMovies
    }

    suspend fun insert(movie:Movie){
        mMovieDao?.insertMovie(movie)
    }
}