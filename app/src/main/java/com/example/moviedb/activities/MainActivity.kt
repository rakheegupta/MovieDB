package com.example.moviedb.activities

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviedb.model.Movie
import com.example.moviedb.MovieAdapter
import com.example.moviedb.MyViewModel
import com.example.moviedb.R

class MainActivity : AppCompatActivity() {

    var movieList= mutableListOf<Movie>()
    var movieAdapter: MovieAdapter? = null
    var movieListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieListView = findViewById(R.id.moviesList)
        movieAdapter = MovieAdapter(this, R.layout.movie_item, movieList)
        movieListView?.adapter = movieAdapter

        if (!isNetworkAvailable()) {
            Toast.makeText(this, "Network unavailable. Try again later.", Toast.LENGTH_LONG).show();
            return;
        }

        var myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.getMovies().observe(this, Observer { movieSnapshot ->
                movieList.clear()
                movieList.addAll(movieSnapshot)
                movieAdapter?.notifyDataSetChanged()
            })

        /*movieAdapter?.movieListener=object : MovieAdapter.MyCustomObjectListener{
            override fun displayMovie(movie :Movie?)
            {
                val intent= Intent(this@MainActivity,MovieSummary::class.java)
                intent.putExtra("movie",movie)
                startActivity(intent)
            }
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }
        }*/
        movieAdapter?.setCustomObjectListener(object : MovieAdapter.MyCustomObjectListener {
            override fun displayMovie(movie : Movie?)
            {
                val intent= Intent(this@MainActivity, MovieSummaryActivity::class.java)
                intent.putExtra("movie",movie)
                startActivity(intent)
            }

            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

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