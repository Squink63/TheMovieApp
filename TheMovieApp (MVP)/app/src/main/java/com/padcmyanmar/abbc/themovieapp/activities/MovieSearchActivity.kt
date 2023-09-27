package com.padcmyanmar.abbc.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.padcmyanmar.abbc.themovieapp.R
import com.padcmyanmar.abbc.themovieapp.adapters.MovieListAdapter
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModel
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.abbc.themovieapp.delegates.MovieViewHolderDelegate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_search.*
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(), MovieViewHolderDelegate {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MovieSearchActivity::class.java)
        }
    }
    //Adapter
    private lateinit var mMovieListAdapter: MovieListAdapter

    //Model
    private val mMovieModel = MovieModelImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        setUpRecyclerView()
        setUpListener()

    }

    private fun setUpListener() {

        edtSearch.textChanges()
            .debounce(500L,TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovie(it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMovieListAdapter.setNewData(it)
            }, {
                showError(it.localizedMessage ?: "")
            })

    }

    private fun setUpRecyclerView() {

        mMovieListAdapter = MovieListAdapter(this)
        rvSearchMovies.adapter = mMovieListAdapter
        rvSearchMovies.layoutManager = GridLayoutManager(this,2)
    }

    private fun showError(showError: String) {
        Snackbar.make(window.decorView,showError, Snackbar.LENGTH_LONG).show()
    }

    override fun onTapMovie(movieId: Int) {

    }
}