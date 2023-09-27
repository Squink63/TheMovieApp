package com.padcmyanmar.abbc.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.abbc.themovieapp.R
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModel
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO
import com.padcmyanmar.abbc.themovieapp.data.vos.GenreVO
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.mvp.presenters.MovieDetailsPresenter
import com.padcmyanmar.abbc.themovieapp.mvp.presenters.MovieDetailsPresenterImpl
import com.padcmyanmar.abbc.themovieapp.mvp.views.MovieDetailsView
import com.padcmyanmar.abbc.themovieapp.mvvm.MovieDetailViewModel
import com.padcmyanmar.abbc.themovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.abbc.themovieapp.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView{

    companion object{

       private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, movieId: Int) : Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }
    // View Pods
    lateinit var actorsViewPod : ActorListViewPod
    lateinit var creatorsViewPod: ActorListViewPod

    // Presenter (MVP)
    private lateinit var mPresenter: MovieDetailsPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setUpPresenter() //MVP


        setUpViewPods()
        setUpListeners()

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0)
        movieId?.let {
            mPresenter.onUiReadyInMovieDetails(this,movieId) // MVP
        }


    }

//    private fun requestData(movieId: Int) {
//        mMovieModel.getMovieDetails(
//            movieId = movieId.toString(),
//            onFailure = {
//                showError(it)
//                }
//        )?.observe(this) {
//            it?.let { movieDetails -> bindData(movieDetails) }
//        }
//
//        mMovieModel.getCreditsByMovie(
//            movieId = movieId.toString(),
//            onSuccess = {
//                actorsViewPod.setData(it.first)
//                creatorsViewPod.setData(it.second)
//            },
//            onFailure = {
//                showError(it)
//            }
//        )
//    }

    // MVP
    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MovieDetailsPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    private fun bindData(movie: MovieVO) {

        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.backDropPath}")
            .into(ivMovieDetail)
        tvMovieName.text = movie.title ?: ""
        ctbMovieDetail.title = movie.title ?: ""
        tvMovieDate.text = movie.releaseDate?.substring(0, 4)
        tvRating.text = movie.voteAverage?.toString() ?: ""
        movie.voteCount?.let {
            tvNumberOfVote.text = "$it VOTES"
        }
        rbMovie.rating = movie.getRatingBasedOnFiveStars()
        bindGenres(movie, movie.genres ?: listOf())
        tvStoryOverview.text = movie.overView ?: ""
        tvOriginalTitle.text = movie.originalTitle ?: ""
        tvType.text = movie.getGenresAsCommaSeparatedString()
        tvProduction.text = movie.getProductionCountriesAsCommaSeparatedString()
        tvPremiere.text = movie.releaseDate ?: ""
        tvDescription.text = movie.overView ?: ""


    }

    private fun bindGenres(movie: MovieVO, genres: List<GenreVO>) {

        movie.genres?.count()?.let {
            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""

            if (it < 3) {
                tvThirdGenre.visibility = View.GONE
            } else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }
        }
    }

    //MVP
    override fun showMovieDetails(movie: MovieVO) {
        bindData(movie)
    }

    override fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>) {
        actorsViewPod.setData(cast)
        actorsViewPod.setData(crew)
    }

    override fun navigateBack() {
        finish()
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView,errorString,Snackbar.LENGTH_LONG).show()
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPods() {
        actorsViewPod = vpActors as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )
        creatorsViewPod = vpCreators as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )

    }
}