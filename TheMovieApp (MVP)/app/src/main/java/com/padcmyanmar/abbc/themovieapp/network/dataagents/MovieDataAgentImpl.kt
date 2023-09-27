package com.padcmyanmar.abbc.themovieapp.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO
import com.padcmyanmar.abbc.themovieapp.data.vos.GenreVO
import com.padcmyanmar.abbc.themovieapp.data.vos.MovieVO
import com.padcmyanmar.abbc.themovieapp.network.responses.MovieListResponse
import com.padcmyanmar.abbc.themovieapp.utils.API_GET_NOW_PLAYING
import com.padcmyanmar.abbc.themovieapp.utils.BASE_URL
import com.padcmyanmar.abbc.themovieapp.utils.MOVIE_API_KEY
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MovieDataAgentImpl : MovieDataAgent {
    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetNowPlayingMovieTask().execute()

    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getMoviesByGenres(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {

    }



    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    class GetNowPlayingMovieTask() : AsyncTask<Void, Void, MovieListResponse?>() {

        override fun doInBackground(vararg p0: Void?): MovieListResponse? {

            val url : URL
            var reader : BufferedReader? = null
            val stringBuilder : StringBuilder

            try {
                //create the HttpURLConnection
                url = URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                //Set Http Method
                connection.requestMethod = "GET"

                //Give time to respond
                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                //Read the output from the server
                reader = BufferedReader(
                    InputStreamReader(connection.inputStream)
                )

                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }
                val respondString = stringBuilder.toString()
                Log.d("NowPlayingMovies", respondString)

                val movieListResponse = Gson().fromJson(
                    respondString,
                    MovieListResponse::class.java
                )
                return movieListResponse
            }

            catch (e: Exception) {
                e.printStackTrace()
                Log.e("NewsError",e.message ?: "")
            }

            return null
        }

        override fun onPostExecute(result: MovieListResponse?) {
            super.onPostExecute(result)
        }
    }
}