package com.padcmyanmar.abbc.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_banner.view.*

class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovie : MovieVO? = null
    init {
        itemView.setOnClickListener {
            mMovie?.let { movie ->
                mDelegate.onTapMovieFromBanner(movie.id)
            }

        }
    }

    fun bindData(movie: MovieVO) {
        mMovie = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.backDropPath}")
            .into(itemView.ivBannerImage)

        itemView.tvMovie.text = movie.title
    }
}