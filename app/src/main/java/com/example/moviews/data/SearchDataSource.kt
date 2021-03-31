package com.example.moviews.data

import com.example.moviews.data.model.Genre
import com.example.moviews.data.model.Movie

interface SearchDataSource {
    interface Remote{
        fun getGenres(callback: OnLoadDataCallback<MutableList<Genre>>)
        fun getTopSearches(callback: OnLoadDataCallback<MutableList<Movie>>)
        fun searchMovie(name:String,callback: OnLoadDataCallback<MutableList<Movie>>)
    }
}
