package com.example.moviews.data.remote

import android.net.Uri
import com.example.moviews.BuildConfig
import com.example.moviews.data.MovieDataSource
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.utils.Constant
import com.example.moviews.utils.parseJsonToObject
import org.json.JSONObject

class MovieRemoteDataSource : MovieDataSource.Remote {

    private fun buildUrl(paths: List<String>) = Uri.Builder()
        .scheme(Constant.BASE_HTTPS)
        .authority(Constant.BASE_AUTHORITY)
        .appendPath(Constant.BASE_VERSION)
        .apply {
            paths.forEach {
                this.appendPath(it)
            }
        }
        .appendQueryParameter(Constant.BASE_KEY, BuildConfig.API_KEY)
        .toString()

    private var urlTrending = buildUrl(
        listOf(
            Constant.BASE_TRENDING,
            Constant.BASE_MOVIE,
            Constant.BASE_TRENDING_TIME
        )
    )
    private var urlUpcoming = buildUrl(
        listOf(
            Constant.BASE_MOVIE,
            Constant.BASE_UPCOMING
        )
    )
    private var urlPopular = buildUrl(
        listOf(
            Constant.BASE_MOVIE,
            Constant.BASE_POPULAR
        )
    )
    private var urlNowPlaying = buildUrl(
        listOf(
            Constant.BASE_MOVIE,
            Constant.BASE_NOW_PLAYING
        )
    )
    private var urlTopSearches = buildUrl(
        listOf(
            Constant.BASE_MOVIE,
            Constant.BASE_TOP_RATE
        )
    )

    private fun getMovie(url: String): MutableList<Movie> =
        JSONObject(readApi(url)).getString(Movie.MOVIE_RESULTS).parseJsonToObject()

    override fun getTrendingMovie(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovie(urlTrending)
        }.execute()
    }

    override fun getUpcomingMovie(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovie(urlUpcoming)
        }.execute()
    }

    override fun getNowPlayingMovie(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovie(urlNowPlaying)
        }.execute()
    }

    override fun getPopularMovie(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovie(urlPopular)
        }.execute()
    }

    override fun getTopSearchMovie(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovie(urlTopSearches)
        }.execute()
    }

    companion object {
        private var instance: MovieRemoteDataSource? = null
        fun getInstance(): MovieRemoteDataSource = instance ?: MovieRemoteDataSource()
            .also { instance = it }
    }
}
