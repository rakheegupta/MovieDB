package com.example.moviedb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel() : ViewModel() {

    private var movieList: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>().also {
        fetchMovies()
    }
    private var bookmarkedMovies: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()

    fun getMovies() :LiveData<MutableList<Movie>>{
        return movieList
    }

    fun getBookmarkedMovies() : LiveData<MutableList<Movie>>{
        return bookmarkedMovies
    }

    private fun fetchMovies() {
        val apiService = TMDBService()
        val call: Call<TMDBMovieResponse> = apiService.getMoviesFromTMDB()

        call.enqueue(object: Callback<TMDBMovieResponse>
        {
            override fun onResponse(call: Call<TMDBMovieResponse>, response: Response<TMDBMovieResponse>)
            {
                val tmdbMovieResponse: TMDBMovieResponse? = response.body()
                if (tmdbMovieResponse!=null)
                {
                    movieList.value =tmdbMovieResponse.results
                    println("response received Total-"+tmdbMovieResponse.results.size)
                }
                else
                    println("NULL")
            }

            override fun onFailure(call: Call<TMDBMovieResponse>, t: Throwable) {
                println(t.message)
                println("reached on faliure ")
            }

        })

    }


}


