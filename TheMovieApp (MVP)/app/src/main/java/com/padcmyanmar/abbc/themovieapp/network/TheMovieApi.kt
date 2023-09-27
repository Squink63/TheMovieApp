package com.padcmyanmar.abbc.themovieapp.network

import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.network.responses.GetActorsResponse
import com.padcmyanmar.abbc.themovieapp.network.responses.GetCreditsByMovieResponse
import com.padcmyanmar.abbc.themovieapp.network.responses.GetGenresResponse
import com.padcmyanmar.abbc.themovieapp.network.responses.MovieListResponse
import com.padcmyanmar.abbc.themovieapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {

    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ) : Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET(API_GET_GENRES)
    fun getGenres(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
    ): Observable<GetGenresResponse>


    @GET(API_GET_MOVIES_BY_GENRES)
    fun getMoviesByGenres(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_GENRE_ID) genreId : String
    ): Observable<MovieListResponse>

    @GET(API_GET_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int =1
    ): Observable<GetActorsResponse>

    @GET("$API_GET_MOVIES_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Observable<MovieVO>

    @GET("$API_GET_CREDITS_BY_MOVIES/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Observable<GetCreditsByMovieResponse>

    @GET(API_SEARCH_MOVIE)
    fun searchMovie(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_QUERY) query: String
    ): Observable<MovieListResponse>

}