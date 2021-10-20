package com.example.moviedb.activities

import android.graphics.Typeface
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviedb.database.MovieDao
import com.example.moviedb.model.Movie
import com.example.moviedb.R
import com.example.moviedb.viewmodel.MyViewModel

class MovieSummaryActivity : AppCompatActivity() {

   // var lateinit mMovie:Movie
   // private lateinit var mMovieViewModel:MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //set layout
        setContentView(R.layout.movie_summary)
        //get intent data
   /*     mMovie = intent.getSerializableExtra("movie") as Movie
        //load views
        val movieImageView= findViewById<ImageView>(R.id.ivMoviePoster)
        val movieNameView = findViewById<TextView>(R.id.tvMovieName)
        val movieOverviewView = findViewById<TextView>(R.id.tvMovieDescription)
        //show image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/" + "original" + movie.backdrop_path)
            .override(resources.displayMetrics.widthPixels,resources.displayMetrics.heightPixels)
            .placeholder(R.drawable.ic_launcher_background)
            .into(movieImageView)
        //show title
        movieNameView.text=movie.original_title
        movieNameView.setTypeface(Typeface.DEFAULT_BOLD)
        //show description
        movieOverviewView.text=movie.overview
        //show related videos use following api
        //https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>&language=en-US
        mMovieViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
   */ }

    /*fun addToFavorites(view: android.view.View) {
        with(view as ImageView) { setImageResource(android.R.drawable.star_big_on) }
        // save to database
        //var myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        mMovieViewModel.insert(MovieTable(movie.id,
                            movie.original_title,
                            movie.backdrop_path,
                            movie.poster_path))
    }*/
}