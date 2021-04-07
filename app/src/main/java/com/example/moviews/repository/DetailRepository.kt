package com.example.moviews.repository

import com.example.moviews.data.MovieDataSource
import com.example.moviews.data.DetailDataSource
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.CastDetail
import com.example.moviews.data.model.Movie
import com.example.moviews.data.model.MovieDetail

class DetailRepository private constructor(
    private val remote: DetailDataSource.Remote,
    private val local: MovieDataSource.Local
) {
    fun getMovieDetail(idMovie: Int, callback: OnLoadDataCallback<MovieDetail>) {
        remote.getMovieDetail(idMovie, callback)
    }

    fun insertMovie(movie: Movie, callback: OnLoadDataCallback<Boolean>) {
        local.insertMovie(movie, callback)
    }

    fun getCastDetail(idCast: Int, callback: OnLoadDataCallback<CastDetail>) {
        remote.getCastDetail(idCast, callback)
    }

    companion object {
        private var instance: DetailRepository? = null
        fun getInstance(
            remote: DetailDataSource.Remote,
            local: MovieDataSource.Local
        ): DetailRepository = instance ?: DetailRepository(remote, local)
            .also { instance = it }
    }
}
