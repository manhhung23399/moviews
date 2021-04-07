package com.example.moviews.data.remote

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.SearchDataSource
import com.example.moviews.data.model.Genre
import com.example.moviews.data.model.Movie
import com.example.moviews.utils.Constant
import com.example.moviews.utils.buildUrl
import com.example.moviews.utils.parseJsonToObject
import org.json.JSONObject

class SearchRemoteDataSource : SearchDataSource.Remote {

    override fun getGenres(callback: OnLoadDataCallback<MutableList<Genre>>) {
        val paths = listOf(
            Constant.BASE_GENRES,
            Constant.BASE_MOVIE,
            Constant.BASE_LIST
        )
        RemoteAsyncTask(callback) {
            val json = readApi(buildUrl(paths))
            JSONObject(json)
                .getString(Genre.GENRES)
                .parseJsonToObject()
        }.execute()
    }

    override fun getTopSearches(callback: OnLoadDataCallback<MutableList<Movie>>) {
        val paths = listOf(
            Constant.BASE_MOVIE,
            Constant.BASE_TOP_RATE
        )
        RemoteAsyncTask(callback) {
            val json = readApi(buildUrl(paths))
            JSONObject(json).getString(Movie.MOVIE_RESULTS)
                .parseJsonToObject()
        }.execute()
    }

    override fun searchMovie(name: String, callback: OnLoadDataCallback<MutableList<Movie>>) {
        val paths = listOf(Constant.BASE_SEARCH, Constant.BASE_MOVIE)
        val queries = mapOf(Constant.BASE_QUERY to name)
        RemoteAsyncTask(callback) {
            val json = readApi(buildUrl(paths, queries))
            JSONObject(json)
                .getString(Movie.MOVIE_RESULTS)
                .parseJsonToObject()
        }.execute()
    }

    companion object {
        private var instance: SearchRemoteDataSource? = null
        fun getInstance(): SearchRemoteDataSource =
            instance ?: SearchRemoteDataSource().also { instance = it }
    }
}
