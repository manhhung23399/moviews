package com.example.moviews.repository

import com.example.moviews.data.MovieDataSource
import com.example.moviews.data.MovieDetailDataSource
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.data.model.MovieDetail

class MovieDetailRepository private constructor(
    private val remote: MovieDetailDataSource.Remote,
    private val local: MovieDataSource.Local
) {
    fun getMovieDetail(idMovie: Int, callback: OnLoadDataCallback<MovieDetail>) {
        remote.getMovieDetail(idMovie, callback)
    }

    fun insertMovie(movie: Movie, callback: OnLoadDataCallback<Boolean>) {
        local.insertMovie(movie, callback)
    }

    companion object {
        private var instance: MovieDetailRepository? = null
        fun getInstance(
            remote: MovieDetailDataSource.Remote,
            local: MovieDataSource.Local
        ): MovieDetailRepository = instance ?: MovieDetailRepository(remote, local)
            .also { instance = it }
    }
}
