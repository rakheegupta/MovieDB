package com.example.moviedb.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviedb.model.Movie

@Dao
interface MovieDao {

    @Query("select * from movie_table where id = :id")
    fun getById(id:Int): Movie

    @Query("select * from movie_table ORDER BY original_title ASC")
    fun getAllFavorites(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie:Movie?):Long

    @Delete
    fun delete(movie:Movie)
}