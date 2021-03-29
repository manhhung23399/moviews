package com.example.moviews.repository

import com.example.moviews.data.MovieDataSource
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.utils.Constant

class MovieRepository private constructor(
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local
) {
    fun getMovies(type: String, callback: OnLoadDataCallback<MutableList<Movie>>) {
        when (type) {
            Constant.BASE_POPULAR -> remote.getPopularMovie(callback)
            Constant.BASE_NOW_PLAYING -> remote.getNowPlayingMovie(callback)
            Constant.BASE_TRENDING -> remote.getTrendingMovie(callback)
            Constant.BASE_UPCOMING -> remote.getUpcomingMovie(callback)
        }
    }

    companion object {
        private var instance: MovieRepository? = null
        fun getInstance(
            remote: MovieDataSource.Remote,
            local: MovieDataSource.Local
        ): MovieRepository = instance ?: MovieRepository(remote, local)
            .also { instance = it }
    }
}
