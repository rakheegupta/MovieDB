package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    var movieList=ArrayList<Movie>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var movieAdapter=MovieAdapter(this,R.layout.movie_item,movieList)
        var movieListView:ListView= findViewById(R.id.moviesList)

        var testMovie=Movie("poster path","simpleTestName of the movie","really long cut short description of the movie")

        fetchPopularMovies(movieAdapter)
        movieListView.adapter=movieAdapter
    }

    fun fetchPopularMovies(movieAdapter:MovieAdapter)
    {
        val apiService = TMDBService()
        val call: Call<TMDBMovieResponse> = apiService.listMovies()

        call.enqueue(object: Callback<TMDBMovieResponse>
        {
            override fun onResponse(call: Call<TMDBMovieResponse>, response: Response<TMDBMovieResponse>)
            {
                val tmdbMovieResponse: TMDBMovieResponse? = response.body()
                if (tmdbMovieResponse!=null)
                {
                    movieList.addAll(tmdbMovieResponse.results)
                    movieAdapter.notifyDataSetChanged()
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