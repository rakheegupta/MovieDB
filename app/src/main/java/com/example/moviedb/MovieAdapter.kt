package com.example.moviedb

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MovieAdapter(context: Context, resourceLayout: Int, moviesList: MutableList<Movie>) :
    ArrayAdapter<Movie>(context, resourceLayout, moviesList) {

    class ViewHolder {
        var moviePosterView: ImageView? = null
        var movieNameView: TextView? = null
        var movieShortDescView: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val thisItem: Movie? = getItem(position)

        val newView: View =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)

        val viewHolder: ViewHolder = if (convertView == null) {
            ViewHolder()
        } else {
            convertView.tag as ViewHolder
        }

        viewHolder.movieNameView = newView.findViewById(R.id.movieNameTextView)
        viewHolder.moviePosterView = newView.findViewById(R.id.moviePosterImageView)
        viewHolder.movieShortDescView = newView.findViewById(R.id.movieDescriptionTextView)

        // Cache the viewHolder object inside the fresh view
        newView.tag = viewHolder

        viewHolder.movieNameView?.text = thisItem?.original_title
        viewHolder.moviePosterView?.setImageResource(R.mipmap.ic_launcher_round)
        viewHolder.movieShortDescView?.text = thisItem?.overview

        println(position.toString() + " " + thisItem?.poster_path)

        //get orientation
        var orientation:Int = context.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //load image
            viewHolder.moviePosterView?.let {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/" + "w500" + thisItem?.backdrop_path)
                    .override(800, 600)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(it)
            }
        } else {

            //load image
            viewHolder.moviePosterView?.let {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/" + "w500" + thisItem?.poster_path)
                    .override(500, 400)
                    .into(it)
            }
        }
        newView.setOnClickListener(
            View.OnClickListener {
                    view ->
                movieListener?.displayMovie(thisItem)
            })

        return newView
    }

    interface MyCustomObjectListener{
        fun displayMovie(movie:Movie?)
    }
    var movieListener : MyCustomObjectListener? =null
    fun setCustomObjectListener(listener: MyCustomObjectListener){
        movieListener=listener
    }

}
/*
        val newView:View =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)

        val moviePosterView:ImageView = newView.findViewById(R.id.moviePosterImageView)
        val movieNameView:TextView = newView.findViewById(R.id.movieNameTextView)
        val movieShortDescView:TextView = newView.findViewById(R.id.movieDescriptionTextView)


        movieNameView.setOnClickListener {
            Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
        }

 *//*
        viewHolder!!.movieNameView?.text ?: thisItem?.original_title
        viewHolder.moviePosterView?.setImageResource(R.mipmap.ic_launcher_round)
        viewHolder.movieShortDescView?.setText(thisItem?.overview)

        println(position.toString()+" "+thisItem?.poster_path)

        //load image
        viewHolder?.moviePosterView?.let {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/"+"w500"+thisItem?.poster_path)
                .override(500,400)
                .into(it)
        }

        return newView*/
