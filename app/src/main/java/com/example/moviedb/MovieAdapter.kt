package com.example.moviedb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide


class MovieAdapter(context: Context, resourceLayout: Int, moviesList: ArrayList<Movie>) :
    ArrayAdapter<Movie>(context, resourceLayout, moviesList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val newView:View =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)

        val moviePosterView:ImageView = newView.findViewById(R.id.moviePosterImageView)
        val movieNameView:TextView=newView.findViewById(R.id.movieNameTextView)
        val movieShortDescView:TextView=newView.findViewById(R.id.movieDescriptionTextView)

        val thisItem: Movie? =getItem(position)
        movieNameView.setOnClickListener {
            Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
        }
        movieNameView.text = thisItem?.original_title
        moviePosterView.setImageResource(R.mipmap.ic_launcher_round)
        movieShortDescView.setText(thisItem?.overview)
        println(message = thisItem?.poster_path)
        //load image
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/"+"w500"+thisItem?.poster_path)
            .override(500,400)
            .into(moviePosterView)
        return newView
    }
}