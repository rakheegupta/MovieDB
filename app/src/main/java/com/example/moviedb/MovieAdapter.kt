package com.example.moviedb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast



class MovieAdapter(context: Context, resourceLayout: Int, moviesList: ArrayList<Movie>) :
    ArrayAdapter<Movie>(context, resourceLayout, moviesList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val newView:View =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)

        val moviePosterView:ImageView = newView.findViewById(R.id.moviePosterImageView)
        val movieNameView:TextView=newView.findViewById(R.id.movieNameTextView)
        val movieShortDescView:TextView=newView.findViewById(R.id.movieDescriptionTextView)

        var thisItem: Movie? =getItem(position)
        movieNameView.setOnClickListener {
            Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
        }
        movieNameView.setText(thisItem?.original_title)
        moviePosterView.setImageResource(R.mipmap.ic_launcher_round)
        movieShortDescView.setText(thisItem?.overview)
        return newView
    }
}