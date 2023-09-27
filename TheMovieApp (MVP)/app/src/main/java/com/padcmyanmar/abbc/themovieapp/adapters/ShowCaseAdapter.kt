package com.padcmyanmar.abbc.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.abbc.themovieapp.R
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.viewholders.ShowCaseViewHolder

class ShowCaseAdapter(private val mDelegate: ShowCaseViewHolderDelegate) : RecyclerView.Adapter<ShowCaseViewHolder>() {

    private var mMovieLIst: List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCaseViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_show_case, parent, false)
        return ShowCaseViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: ShowCaseViewHolder, position: Int) {
        if (mMovieLIst.isNotEmpty()) {
            holder.bindData(mMovieLIst[position])
        }
    }

    override fun getItemCount(): Int {
    return mMovieLIst.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList : List<MovieVO>) {
        mMovieLIst = movieList
        notifyDataSetChanged()
    }
}