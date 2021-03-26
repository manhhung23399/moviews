package com.example.moviews.data.local

import com.example.moviews.data.MovieDataSource
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.local.dao.MovieDao
import com.example.moviews.data.model.Movie

class MovieLocalDataSource private constructor(
    private val movieDao: MovieDao
) : MovieDataSource.Local {

    override fun insertMovie(movie: Movie, callback: OnLoadDataCallback<Boolean>) {
        LocalAsyncTask<Movie, Boolean>(callback) {
            movieDao.insertMovie(movie)
        }.execute(movie)
    }

    override fun deleteMovie(movieId: String, callback: OnLoadDataCallback<Boolean>) {
        LocalAsyncTask<String, Boolean>(callback) {
            movieDao.deleteMovie(movieId)
        }.execute(movieId)
    }

    override fun getAllMovies(callback: OnLoadDataCallback<MutableList<Movie>>) {
        LocalAsyncTask<Unit, MutableList<Movie>>(callback) {
            movieDao.getAllMovies()
        }.execute()
    }
}
