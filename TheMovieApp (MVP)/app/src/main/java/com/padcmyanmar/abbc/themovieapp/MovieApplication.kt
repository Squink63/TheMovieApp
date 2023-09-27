package com.padcmyanmar.abbc.themovieapp

import android.app.Application
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModelImpl

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}