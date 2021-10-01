package com.example.moviedb

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class MyViewModel() : ViewModel(){

    private var movieList: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>().also {
        fetchMovies()
    }

    fun getMovies() :LiveData<MutableList<Movie>>{
        return movieList
    }

    private fun fetchMovies() {
        val apiService = TMDBService()
        val call: Call<TMDBMovieResponse> = apiService.listMovies()

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
                println("reached on faliure list")
            }

        })

    }


}


