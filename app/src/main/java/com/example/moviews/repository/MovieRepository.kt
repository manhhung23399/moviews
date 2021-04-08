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
            Constant.BASE_POPULAR -> remote.getPopularMovies(callback)
            Constant.BASE_NOW_PLAYING -> remote.getNowPlayingMovies(callback)
            Constant.BASE_TRENDING -> remote.getTrendingMovies(callback)
            Constant.BASE_UPCOMING -> remote.getUpcomingMovies(callback)
        }
    }

    fun getFavoriteMovies(callback: OnLoadDataCallback<MutableList<Movie>>) =
        local.getAllMovies(callback)

    fun deleteMovies(idMovie: String, callback: OnLoadDataCallback<Boolean>) =
        local.deleteMovie(idMovie, callback)

    fun getMovieByGenres(idGenre: Int, callback: OnLoadDataCallback<MutableList<Movie>>) =
        remote.getMovieByGenres(idGenre, callback)

    companion object {
        private var instance: MovieRepository? = null
        fun getInstance(
            remote: MovieDataSource.Remote,
            local: MovieDataSource.Local
        ): MovieRepository = instance ?: MovieRepository(remote, local)
            .also { instance = it }
    }
}
