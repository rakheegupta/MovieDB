package com.example.moviedb

import Movie
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MovieAdapter(context: Context, resourceLayout: Int, moviesList: ArrayList<Movie>) :
    ArrayAdapter<Movie>(context, resourceLayout, moviesList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val newView:View =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)

        val moviePosterView:ImageView = newView.findViewById(R.id.moviePosterImageView)
        val movieNameView:TextView=newView.findViewById(R.id.movieNameTextView)
        val movieShortDescView:TextView=newView.findViewById(R.id.movieDescriptionTextView)

        movieNameView.setText("a")
        moviePosterView.setImageResource(R.drawable.ic_launcher_foreground)
        movieShortDescView.setText("b")

        return newView
    }
}