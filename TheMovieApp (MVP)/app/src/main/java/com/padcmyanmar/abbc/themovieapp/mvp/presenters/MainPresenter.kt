package com.padcmyanmar.abbc.themovieapp.mvp.presenters

import com.padcmyanmar.abbc.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.mvp.views.MainView

interface MainPresenter : IBasePresenter, BannerViewHolderDelegate, ShowCaseViewHolderDelegate, MovieViewHolderDelegate {

    fun initView(view: MainView)
    fun onTapGenre(genrePosition: Int)

}