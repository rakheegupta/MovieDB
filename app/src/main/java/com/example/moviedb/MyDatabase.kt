package com.example.moviedb.com.example.moviedb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedb.MovieDao
import com.example.moviedb.model.Movie

// bump version number if your schema changes
@Database(entities = [Movie::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    // Declare your data access objects as abstract
    abstract fun movieDao(): MovieDao?

    companion object {
        // Database name to be used
        const val NAME = "MyDataBase"
    }
}