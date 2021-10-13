package com.example.moviedb.model

import java.io.Serializable

public class Movie (moviePoster: String,
                    movieName: String,
                    briefSummary: String,
                    backdropPath: String): Serializable
{
    private val serialVersionUID = 5177222050535318633L
    val original_title: String=movieName
    val poster_path:String=moviePoster
    val overview:String=briefSummary
    val backdrop_path:String=backdropPath
}