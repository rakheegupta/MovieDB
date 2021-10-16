package com.example.moviedb

import android.app.Application
import androidx.room.Room
import com.example.moviedb.com.example.moviedb.MyDatabase

class MyTMDBApplication: Application() {
    private var myDatabase : MyDatabase? = null

    override fun onCreate() {
        super.onCreate()
        myDatabase=Room.databaseBuilder(applicationContext, MyDatabase::class.java, MyDatabase.NAME)
            .fallbackToDestructiveMigration()
            .build()
        print("myTMDBApplication class was used for initialization")
    }

    fun getMyDatabase(): MyDatabase? {
        return myDatabase
    }
}