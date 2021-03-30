package com.example.moviews.utils

import android.content.Context
import com.example.moviews.data.local.MovieLocalDataSource
import com.example.moviews.data.local.dao.MovieDaoImpl
import com.example.moviews.data.local.database.AppDatabase
import com.example.moviews.repository.MovieRepository
import com.example.moviews.data.remote.MovieRemoteDataSource

object RepositoryUtils {
    fun getMovieRepository(context: Context):MovieRepository{
        val database = AppDatabase.getInstance(context)
        val movieDaoImpl = MovieDaoImpl.getInstance(database)
        val local = MovieLocalDataSource.getInstance(movieDaoImpl)
        val remote = MovieRemoteDataSource.getInstance()
        return MovieRepository.getInstance(remote,local)
    }
}
