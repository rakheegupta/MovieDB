package com.example.moviedb.activities

import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviedb.model.Movie
import com.example.moviedb.R

class MovieSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set layout
        setContentView(R.layout.movie_summary)
        //get intent data
        var movie= intent.getSerializableExtra("movie") as Movie?
        //load views
        val movieImageView= findViewById<ImageView>(R.id.ivMoviePoster)
        val movieNameView = findViewById<TextView>(R.id.tvMovieName)
        val movieOverviewView = findViewById<TextView>(R.id.tvMovieDescription)
        //show image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/" + "original" + movie?.backdrop_path)
            .override(resources.displayMetrics.widthPixels,resources.displayMetrics.heightPixels/2)
            .placeholder(R.drawable.ic_launcher_background)
            .into(movieImageView)
        //show title
        movieNameView.text=movie?.original_title
        movieNameView.setTypeface(Typeface.DEFAULT_BOLD)
        //show description
        movieOverviewView.text=movie?.overview
        //show related videos use following api
        //https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>&language=en-US
    }
}