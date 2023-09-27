package com.padcmyanmar.abbc.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModel
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.abbc.themovieapp.data.vos.GenreVO
import com.padcmyanmar.abbc.themovieapp.interactors.MovieInteractor
import com.padcmyanmar.abbc.themovieapp.interactors.MovieInteractorImpl
import com.padcmyanmar.abbc.themovieapp.mvp.views.MainView

class MainPresenterImpl: ViewModel(), MainPresenter {

    //View
    var mView: MainView? = null

    //Model
    private val mMovieModel: MovieModel = MovieModelImpl

    //Interactor
    private val mMovieInteractor: MovieInteractor = MovieInteractorImpl

    //States
    private var mGenres: List<GenreVO>? = listOf()

    override fun initView(view: MainView) {

        mView = view
    }


    override fun onUiReady(owner: LifecycleOwner) {

        //NowPlayingMovies
        mMovieModel.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showNowPlayingMovies(it)
        }

        //PopularMovies
        mMovieModel.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showPopularMovies(it)
        }

        //TopRatedMovies
        mMovieModel.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showTopRatedMovies(it)
        }

        //Genres and Get Movies for First Genre
        mMovieModel.getGenres(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let { firstGenreId ->
                    onTapGenre(firstGenreId)
                }
            }, onFailure = {
                mView?.showError(it)
            }
        )

        //Actors
        mMovieModel.getActors(
            onSuccess = {
                mView?.showActors(it)
            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapMovieFromBanner(movieId: Int) {

        mView?.navigateToMovieDetailScreen(movieId)
    }

    override fun onTapMovieFromShowCase(movieId: Int) {

        mView?.navigateToMovieDetailScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {

        mView?.navigateToMovieDetailScreen(movieId)
    }

    override fun onTapGenre(genrePosition: Int) {

        mGenres?.getOrNull(genrePosition)?.id?.let { genreId ->
            mMovieModel.getMoviesByGenres(genreId = genreId.toString(), onSuccess = {
                mView?.showMoviesByGenres(it)
            }, onFailure = {
                mView?.showError(it)
            })
        }
    }
}