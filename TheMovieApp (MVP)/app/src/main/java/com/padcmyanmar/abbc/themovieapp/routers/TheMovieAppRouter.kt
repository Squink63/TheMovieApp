package com.padcmyanmar.abbc.themovieapp.routers

import android.app.Activity
import com.padcmyanmar.abbc.themovieapp.activities.MovieDetailsActivity
import com.padcmyanmar.abbc.themovieapp.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailsActivity(movieId: Int) {
    startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
}

fun Activity.navigateToMovieSearchActivity() {
    startActivity(MovieSearchActivity.newIntent(this))
}