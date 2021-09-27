package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView


class MainActivity : AppCompatActivity() {

    private val movieClient=TMDBClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var movieListView:ListView= findViewById(R.id.moviesList)

        var testList=ArrayList<String>()

        testList.add("a")
        testList.add("b")
        testList.add("c")

        //json call
        //https://api.themoviedb.org/3/movie/550?api_key=
        var simpleMovieAdapter=ArrayAdapter(this,R.layout.movie_item,R.id.movieNameTextView,testList)
        movieListView.setAdapter(simpleMovieAdapter)

        var movieList=ArrayList<Movie>()
        var testMovie=Movie("poster path","simpleTestName of the movie","really long cut short description of the movie")
        movieList.add(testMovie)
        movieList.add(testMovie)
        movieList.add(testMovie)
        movieList.add(testMovie)
        movieList.add(testMovie)
        movieList.add(testMovie)
        movieList.add(testMovie)


        var movieAdapter=MovieAdapter(this,R.layout.movie_item,movieList).also {movieListView.adapter=it}
        movieClient.fetchPopularMovies()

    }

}