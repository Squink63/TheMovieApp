package com.padcmyanmar.abbc.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModel
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO
import com.padcmyanmar.abbc.themovieapp.data.vos.GenreVO
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO

class MainViewModel: ViewModel() {

    //Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var nowPlayingMovieLivedata: LiveData<List<MovieVO>>? = null
    var popularMovieLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMovieLivedata: LiveData<List<MovieVO>>? = null
    val genresLivedata = MutableLiveData<List<GenreVO>>()
    val moviesByGenresLivedata = MutableLiveData<List<MovieVO>>()
    val actorsLivedata= MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData() {

        nowPlayingMovieLivedata = mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMovieLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }
        topRatedMovieLivedata = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }

        mMovieModel.getGenres(
            onSuccess = {
                genresLivedata.postValue(it)
                getMovieByGenre(0)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                actorsLivedata.postValue(it)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }


    fun getMovieByGenre(genrePosition: Int) {
        genresLivedata.value?.getOrNull(genrePosition)?.id?.let {
            mMovieModel.getMoviesByGenres(it.toString(), onSuccess =  { movieByGenre ->
                moviesByGenresLivedata.postValue(movieByGenre)
            }, onFailure = { errorMessage ->
                mErrorLiveData.postValue(errorMessage)
            })
        }
    }
}