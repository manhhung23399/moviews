package com.example.moviews.repository

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.SearchDataSource
import com.example.moviews.data.model.Genre
import com.example.moviews.data.model.Movie

class SearchRepository private constructor(
    private val remote: SearchDataSource.Remote
) {
    fun getGenres(callback: OnLoadDataCallback<MutableList<Genre>>) {
        remote.getGenres(callback)
    }

    fun getTopSearches(callback: OnLoadDataCallback<MutableList<Movie>>) {
        remote.getTopSearches(callback)
    }
    fun searchMovie(name:String,callback: OnLoadDataCallback<MutableList<Movie>>){
        remote.searchMovie(name,callback)
    }
    companion object {
        private var instance: SearchRepository? = null
        fun getInstance(remote: SearchDataSource.Remote): SearchRepository =
            instance ?: SearchRepository(remote).also { instance = it }
    }
}
