package com.padcmyanmar.abbc.themovieapp.mvp.views

import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO

interface MovieDetailsView: BaseView {
    fun showMovieDetails(movie: MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
}