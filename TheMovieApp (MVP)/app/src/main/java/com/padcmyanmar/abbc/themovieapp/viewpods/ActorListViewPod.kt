package com.padcmyanmar.abbc.themovieapp.viewpods

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.abbc.themovieapp.adapters.BestActorAdapter
import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*


class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mBestActorAdapter: BestActorAdapter

    override fun onFinishInflate() {
        setUpBestActorRecyclerView()
        super.onFinishInflate()
    }

    fun setUpActorViewPod(backgroundColorReference: Int, titleText: String, moreTitleText: String ) {
        setBackgroundColor(ContextCompat.getColor(context, backgroundColorReference))
        tvBestActors.text = titleText
        tvMoreActors.text = moreTitleText
        tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG

    }

    fun setData (actors : List<ActorVO>) {
        mBestActorAdapter.setNewData(actors)
    }

    private fun setUpBestActorRecyclerView() {
        mBestActorAdapter = BestActorAdapter()
        rvBestActors.adapter = mBestActorAdapter
        rvBestActors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}