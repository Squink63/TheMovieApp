package com.padcmyanmar.abbc.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie_list.view.*

class MovieListViewHolder(itemView: View, private val mDelegate: MovieViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {


    private var mMovieVO : MovieVO? = null
    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                mDelegate.onTapMovie(it.id)
            }

        }
    }

    fun bindData(movie : MovieVO) {
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.ivMovieImage)
        itemView.tvMovieListName.text = movie.title
        itemView.tvMovieRating.text = movie.voteAverage?.toString()
        itemView.rbMovieRating.rating = movie.getRatingBasedOnFiveStars()
    }
}