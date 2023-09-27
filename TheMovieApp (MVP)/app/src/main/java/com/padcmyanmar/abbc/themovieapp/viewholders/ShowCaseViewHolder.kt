package com.padcmyanmar.abbc.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_show_case.view.*

class ShowCaseViewHolder(itemView: View, private val mDelegate: ShowCaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO : MovieVO? = null
    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                mDelegate.onTapMovieFromShowCase(it.id)
            }

        }
    }

    fun bindData(movie: MovieVO) {
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivShowCase)
        itemView.tvShowCaseMovieName.text = movie.title
        itemView.tvShowCaseMovieDate.text = movie.releaseDate
    }

}