package com.example.moviedb.activities

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.model.Movie
import com.example.moviedb.viewmodel.MyViewModel

class MovieSummaryActivity : AppCompatActivity() {

    private lateinit var mMovie: Movie
    private lateinit var mMovieViewModel: MyViewModel
    private var isFav:Boolean =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_summary)
        //get intent data
        mMovie = intent.getParcelableExtra("movie")!!
        isFav = intent.getBooleanExtra("isFavMovie",false)

        /* load views */
        val movieImageView= findViewById<ImageView>(R.id.ivMoviePoster)
        val movieNameView = findViewById<TextView>(R.id.tvMovieName)
        val movieOverviewView = findViewById<TextView>(R.id.tvMovieDescription)
        val starMovieView = findViewById<ImageView>(R.id.ivAddToFavorite)

        //show image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/" + "original" + mMovie.backdrop_path)
            .override(resources.displayMetrics.widthPixels,resources.displayMetrics.heightPixels)
            .placeholder(R.drawable.ic_launcher_background)
            .into(movieImageView)
        //show title
        movieNameView.text=mMovie.original_title
        movieNameView.setTypeface(Typeface.DEFAULT_BOLD)
        //show description
        movieOverviewView.text=mMovie.overview
        //show related videos use following api
        //https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>&language=en-US
        mMovieViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        if(isFav)
            starMovieView.setImageResource(android.R.drawable.star_big_on)

        actionBar?.setTitle(mMovie.original_title)

    }

    fun addToFavorites(view: android.view.View) {
        with(view as ImageView) { setImageResource(android.R.drawable.star_big_on) }
        // save to database
        //var myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        mMovieViewModel.insert(mMovie)
    }
}