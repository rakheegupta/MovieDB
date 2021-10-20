package com.example.moviedb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "movie_table")
public class Movie (@PrimaryKey
                    val id:Int,
                    val original_title: String,
                    val poster_path:String,
                    val overview:String,
                    val backdrop_path:String): Parcelable