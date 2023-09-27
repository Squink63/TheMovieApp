package com.padcmyanmar.abbc.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.abbc.themovieapp.R
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.viewholders.MovieListViewHolder

class MovieListAdapter(private val mDelegate : MovieViewHolderDelegate) : RecyclerView.Adapter<MovieListViewHolder>() {

    private var mMovieList : List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
      val view =  LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_list, parent, false)
        return MovieListViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    fun setNewData(movieList : List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }
}