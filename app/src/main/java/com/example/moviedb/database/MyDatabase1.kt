package com.example.moviedb.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviedb.model.Movie

@Database(entities = [Movie::class], version = 2, exportSchema = false)
abstract class MyDatabase1 : RoomDatabase() {

    // Declare your data access objects as abstract
    abstract fun movieDao(): MovieDao

    companion object {
        // Database name to be used
        private const val NAME = "MyDatabase"
        @Volatile
        private var INSTANCE: MyDatabase1? = null

        fun getDatabase(application: Application): MyDatabase1? {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            application.applicationContext,
                            MyDatabase1::class.java,
                            NAME
                        ) // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}