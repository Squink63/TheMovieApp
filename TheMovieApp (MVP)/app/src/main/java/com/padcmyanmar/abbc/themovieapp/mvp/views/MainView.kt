package com.padcmyanmar.abbc.themovieapp.mvp.views

import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO
import com.padcmyanmar.abbc.themovieapp.data.vos.GenreVO
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO

interface MainView : BaseView {

    fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovies(popularMovies: List<MovieVO>)
    fun showTopRatedMovies(topRatedMovies: List<MovieVO>)
    fun showGenres(genreList: List<GenreVO>)
    fun showMoviesByGenres(moviesByGenres: List<MovieVO>)
    fun showActors(actors: List<ActorVO>)
    fun navigateToMovieDetailScreen(movieId: Int)
}