package com.example.moviews.data.remote

import com.example.moviews.data.MovieDataSource
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.utils.Constant
import com.example.moviews.utils.buildUrl
import com.example.moviews.utils.parseJsonToObject
import org.json.JSONObject

class MovieRemoteDataSource : MovieDataSource.Remote {

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

    private fun getMovies(url: String): MutableList<Movie> =
        JSONObject(readApi(url)).getString(Movie.MOVIE_RESULTS).parseJsonToObject()

    override fun getTrendingMovies(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovies(urlTrending)
        }.execute()
    }

    override fun getUpcomingMovies(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovies(urlUpcoming)
        }.execute()
    }

    override fun getNowPlayingMovies(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovies(urlNowPlaying)
        }.execute()
    }

    override fun getPopularMovies(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovies(urlPopular)
        }.execute()
    }

    override fun getTopSearchMovies(callback: OnLoadDataCallback<MutableList<Movie>>) {
        RemoteAsyncTask(callback) {
            getMovies(urlTopSearches)
        }.execute()
    }

    override fun getMovieByGenres(idGenre: Int, callback: OnLoadDataCallback<MutableList<Movie>>) {
        val paths = listOf(
            BASE_DISCOVER,
            Constant.BASE_MOVIE
        )
        val queries = mapOf(
            BASE_WITH_GENRES to idGenre.toString()
        )
        RemoteAsyncTask(callback) {
            val json = readApi(buildUrl(paths, queries))
            JSONObject(json)
                .getString(Movie.MOVIE_RESULTS)
                .parseJsonToObject()
        }.execute()
    }

    companion object {
        const val BASE_DISCOVER = "discover"
        const val BASE_WITH_GENRES = "with_genres"
        private var instance: MovieRemoteDataSource? = null
        fun getInstance(): MovieRemoteDataSource = instance ?: MovieRemoteDataSource()
            .also { instance = it }
    }
}
