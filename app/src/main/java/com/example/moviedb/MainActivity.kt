package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
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
        var simpleMovieAdapter=ArrayAdapter<String>(this,R.layout.movie_item,R.id.movieName,testList)
        movieListView.setAdapter(simpleMovieAdapter)
    }
}