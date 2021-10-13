package com.example.moviedb

import androidx.room.*
import com.example.moviedb.model.Movie

@Dao
interface MovieDao {

    @Query("select * from movie_table where id = :id")
    fun getById(id:Int):Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie:Movie):Long

    @Delete
    fun delete(movie:Movie)
}