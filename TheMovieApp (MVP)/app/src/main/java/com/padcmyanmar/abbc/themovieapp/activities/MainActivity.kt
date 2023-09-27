package com.padcmyanmar.abbc.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.abbc.themovieapp.R
import com.padcmyanmar.abbc.themovieapp.adapters.BannerAdapter
import com.padcmyanmar.abbc.themovieapp.adapters.ShowCaseAdapter
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModel
import com.padcmyanmar.abbc.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO
import com.padcmyanmar.abbc.themovieapp.data.vos.GenreVO
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.padcmyanmar.abbc.themovieapp.dummy.dummyGenreList
import com.padcmyanmar.abbc.themovieapp.mvp.presenters.MainPresenter
import com.padcmyanmar.abbc.themovieapp.mvp.presenters.MainPresenterImpl
import com.padcmyanmar.abbc.themovieapp.mvp.views.MainView
import com.padcmyanmar.abbc.themovieapp.mvvm.MainViewModel
import com.padcmyanmar.abbc.themovieapp.network.dataagents.MovieDataAgentImpl
import com.padcmyanmar.abbc.themovieapp.viewpods.ActorListViewPod
import com.padcmyanmar.abbc.themovieapp.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    MainView {

    //ViewPods
    lateinit var mBannerAdapter : BannerAdapter
    lateinit var mShoeCaseAdapter : ShowCaseAdapter

    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMoviesByGenreViewPod : MovieListViewPod
    lateinit var mActorListViewPod : ActorListViewPod

    //Presenter(MVP)
    private lateinit var mPresenter: MainPresenter


    //ViewModel (MVVM)
//    private lateinit var mViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()  // MVP
//

        setUpToolbar()
        setUpViewPods()
        setUpBannerViewPager()
        setUpShowCaseRecyclerView()
        setUpListeners()

        mPresenter.onUiReady(this) // MVP



    }

    // MVP
    private fun setUpPresenter() {

        mPresenter= ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    //ViewPods
    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(mPresenter)

        mMoviesByGenreViewPod = vpMoviesByGenre as MovieListViewPod
        mMoviesByGenreViewPod.setUpMovieListViewPod(mPresenter)

        mActorListViewPod = vpBestActor as ActorListViewPod
    }

    //Banner
    private fun setUpBannerViewPager() {
        mBannerAdapter=BannerAdapter(mPresenter)
        viewPagerBanner.adapter=mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    // App Bar Icon
    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }
    //Genre TabLayout
    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
          tabLayoutGenre.newTab().apply {
              text = it.name
              tabLayoutGenre.addTab(this)
          }
        }
    }

    private fun setUpListeners() {

        //Genre TabLayout
        tabLayoutGenre.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPresenter.onTapGenre(tab?.position?: 0)  //MVP
//                Snackbar.make(window.decorView, tab?.text ?: "", Snackbar.LENGTH_LONG).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        } )


    }

    //Movie ShowCase
    private fun setUpShowCaseRecyclerView() {
        mShoeCaseAdapter = ShowCaseAdapter(mPresenter)
        rvShowCase.adapter = mShoeCaseAdapter
        rvShowCase.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(MovieSearchActivity.newIntent(this))
        return true
    }

    ///  MVP

    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMovieListViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShoeCaseAdapter.setNewData(topRatedMovies)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        setUpGenreTabLayout(genreList)
    }

    override fun showMoviesByGenres(moviesByGenres: List<MovieVO>) {
        mMoviesByGenreViewPod.setData(moviesByGenres)
    }

    override fun showActors(actors: List<ActorVO>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailScreen(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun showError(errorString: String) {

    }

//    private fun requestData() {
//
//        //Now Playing Movies
//        mMovieModel.getNowPlayingMovies(
//            onFailure = {
//                showError(it)
//            }
//        )?.observe(this) {
//            mBannerAdapter.setNewData(it)
//        }
//
//        //Popular Movies
//        mMovieModel.getPopularMovies(
//            onFailure = {
//                showError(it)
//            }
//        )?.observe(this) {
//            mBestPopularMovieListViewPod.setData(it)
//        }
//
//        //Top Rated Movies
//        mMovieModel.getTopRatedMovies(
//            onFailure = {
//                showError(it)
//            }
//        )?.observe(this) {
//            mShoeCaseAdapter.setNewData(it)
//        }
//
//        //Get Genres
//        mMovieModel.getGenres(
//            onSuccess = {
//                mGenres = it
//                setUpGenreTabLayout(it)
//
//                //Get Movies by Genres For the First Genre
//                it.firstOrNull()?.id?.let { genreId ->
//                    getMoviesByGenres(genreId)
//                }
//            },
//            onFailure = {
//
//            }
//
//        )
//
//        //Get actors
//        mMovieModel.getActors(
//            onSuccess = {
//                mActorListViewPod.setData(it)
//            },
//
//            onFailure = {
//
//            }
//        )
//
//    }
//
//    private fun getMoviesByGenres(genreId : Int) {
//        mMovieModel.getMoviesByGenres(genreId = genreId.toString(),
//        onSuccess = {
//            mMoviesByGenreViewPod.setData(it)
//        },
//        onFailure = {
//
//        })
//    }
//
//    private fun showError(showError: String) {
//        Snackbar.make(window.decorView,showError,Snackbar.LENGTH_LONG).show()
//    }
}