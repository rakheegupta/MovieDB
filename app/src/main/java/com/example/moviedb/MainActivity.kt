package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    //private val LIST_STATE: String? ="liststate"
    //private var mListState: Parcelable?= null

    var movieList= mutableListOf<Movie>()
    var movieAdapter:MovieAdapter? = null
    var movieListView:ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieListView= findViewById(R.id.moviesList)
        movieAdapter=MovieAdapter(this,R.layout.movie_item,movieList)
        movieListView?.adapter=movieAdapter

        //var viewModel1 :MyViewModel by MyViewModel(application)
        //var myViewModel=ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MyViewModel::class.java)
        //use get() instead of create() everytime configuration changes

        var myViewModel= ViewModelProvider(this).get(MyViewModel::class.java)

        var testMovie=Movie("poster path","simpleTestName of the movie","really long cut short description of the movie")

        //fetchPopularMovies(movieAdapter!!)
        myViewModel.getMovies().observe(this, Observer{ movieSnapshot ->
            movieList.clear()
            movieList.addAll(movieSnapshot)
            movieAdapter?.notifyDataSetChanged()
        })


    }

    /*fun fetchPopularMovies(movieAdapter:MovieAdapter)
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
                    moviesLoaded=true
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
    }*/
/*
    override protected fun onRestart() {
        super.onRestart()
        if(!moviesLoaded) {
            if (movieAdapter == null)
                movieAdapter = MovieAdapter(this, R.layout.movie_item, movieList)
            fetchPopularMovies(movieAdapter!!)
        }
        if(mListState != null){
            movieListView?.onRestoreInstanceState(mListState)
            mListState = null
        }
    }

    protected override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mListState= savedInstanceState.getParcelable(LIST_STATE)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mListState = movieListView?.onSaveInstanceState()
        outState.putParcelable(LIST_STATE,mListState)
    }
*/
}