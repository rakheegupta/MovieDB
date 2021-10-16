package com.example.moviedb.model

import androidx.room.ColumnInfo
import java.io.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
public class Movie (moviePoster: String,
                    movieName: String,
                    briefSummary: String,
                    backdropPath: String,
                    id:Int): Serializable
{
    @ColumnInfo(name = "id")
    @PrimaryKey()
    public val movieId:Int=id

    private val serialVersionUID = 5177222050535318633L
    @ColumnInfo
    public val original_title: String=movieName
    @ColumnInfo
    public val poster_path:String=moviePoster
    @ColumnInfo
    public val overview:String=briefSummary
    @ColumnInfo
    public val backdrop_path:String=backdropPath
}