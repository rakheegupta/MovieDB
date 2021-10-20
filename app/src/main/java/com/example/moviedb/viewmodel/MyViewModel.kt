package com.example.moviedb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.database.MovieRepository
import com.example.moviedb.model.Movie
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import com.example.moviedb.database.MyDatabase1
import kotlinx.coroutines.launch

class MyViewModel(application:Application) : AndroidViewModel(application) {

    private var movieList: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>().also {
        fetchMovies()
    }

    private val bookmarkedMovies: LiveData<List<Movie>>?
    private val mMovieRepository: MovieRepository

    init{
        val mMovieDao = MyDatabase1.getDatabase(application)?.movieDao()
        mMovieRepository = MovieRepository(mMovieDao)
        bookmarkedMovies = mMovieRepository.getFavoriteMovies()
    }
    fun getMovies() :LiveData<MutableList<Movie>>{
        return movieList
    }

    fun getBookmarkedMovies() : LiveData<List<Movie>>?{
        return bookmarkedMovies
    }

    public fun insert(movie: Movie){
        viewModelScope.launch(Dispatchers.IO){
            mMovieRepository.insert(movie)
        }
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


