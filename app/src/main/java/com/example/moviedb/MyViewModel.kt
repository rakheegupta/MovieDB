package com.example.moviedb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.flow.Flow

class MyViewModel(application: Application) : AndroidViewModel(application){

    private val movieList: MutableLiveData<MutableList<Movie>> by lazy {
        MutableLiveData<MutableList<Movie>>().also {
            fetchMovies()
        }
    }

    //private val movieAdapter:MovieAdapter= MovieAdapter(application.baseContext,0,movieList)

    fun getMovies() :LiveData<MutableList<Movie>>{
        return movieList
    }

    private fun fetchMovies(){
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
                    //var flowData=tmdbMovieResponse.results as Flow<ArrayList<Movie>>

                    //movieAdapter.notifyDataSetChanged()
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


