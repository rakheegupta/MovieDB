package com.example.moviedb

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MovieSummary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_summary)
        var movie= intent.getSerializableExtra("movie") as Movie?
        val movieImageView= findViewById<ImageView>(R.id.ivMoviePoster)
        val movieNameView = findViewById<TextView>(R.id.tvMovieName)
        val movieOverviewView = findViewById<TextView>(R.id.tvMovieDescription)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/" + "w500" + movie?.backdrop_path)
            .override(800, 600)
            .placeholder(R.drawable.ic_launcher_background)
            .into(movieImageView)
        movieNameView.text=movie?.original_title
        movieNameView.setTypeface(Typeface.DEFAULT_BOLD)
        movieOverviewView.text=movie?.overview
    }
}