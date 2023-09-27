package com.padcmyanmar.abbc.themovieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.abbc.themovieapp.adapters.MovieListAdapter
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mMovieListAdapter : MovieListAdapter
    lateinit var mDelegate : MovieViewHolderDelegate

    override fun onFinishInflate() {
        //setUpMovieListRecyclerView()
        super.onFinishInflate()
    }

    fun setData(movieList: List<MovieVO>) {
        mMovieListAdapter.setNewData(movieList)
    }

    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate) {
        setDelegate(delegate)
        setUpMovieListRecyclerView()
    }

    private fun setDelegate(delegate: MovieViewHolderDelegate) {
        this.mDelegate = delegate
    }

    private fun setUpMovieListRecyclerView() {
        mMovieListAdapter = MovieListAdapter(mDelegate)
        rvMovieList.adapter = mMovieListAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}