package com.padcmyanmar.abbc.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO

class MovieDetailViewModel: ViewModel() {

    //Model
    private val mMovieModel = MovieModelImpl

    //Live Data
    var movieDetailLiveData : LiveData<MovieVO?>? = null
    val castLiveData = MutableLiveData<List<ActorVO>>()
    val crewLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(movieId: Int) {

        movieDetailLiveData =
            mMovieModel.getMovieDetails(movieId = movieId.toString()) { mErrorLiveData.postValue(it)}

        mMovieModel.getCreditsByMovie(movieId.toString(), onSuccess = {
            castLiveData.postValue(it.first ?: listOf())
            crewLiveData.postValue(it.second ?: listOf())
        }, onFailure = {
            mErrorLiveData.postValue(it)
        })

    }

}