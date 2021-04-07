package com.example.moviews.repository

import android.content.Context
import com.example.moviews.data.local.MovieLocalDataSource
import com.example.moviews.data.local.dao.MovieDaoImpl
import com.example.moviews.data.local.database.AppDatabase
import com.example.moviews.data.remote.DetailRemoteDataSource
import com.example.moviews.data.remote.MovieRemoteDataSource
import com.example.moviews.data.remote.SearchRemoteDataSource

object RepositoryUtils {
    fun getMovieRepository(context: Context): MovieRepository {
        val database = AppDatabase.getInstance(context)
        val movieDaoImpl = MovieDaoImpl.getInstance(database)
        val local = MovieLocalDataSource.getInstance(movieDaoImpl)
        val remote = MovieRemoteDataSource.getInstance()
        return MovieRepository.getInstance(remote, local)
    }

    fun getSearchRepository(): SearchRepository {
        val remote = SearchRemoteDataSource.getInstance()
        return SearchRepository.getInstance(remote)
    }

    fun getDetailRepository(context: Context): DetailRepository {
        val database = AppDatabase.getInstance(context)
        val movieDaoImpl = MovieDaoImpl.getInstance(database)
        val local = MovieLocalDataSource.getInstance(movieDaoImpl)
        val remote = DetailRemoteDataSource.getInstance()
        return DetailRepository.getInstance(remote, local)
    }
}
