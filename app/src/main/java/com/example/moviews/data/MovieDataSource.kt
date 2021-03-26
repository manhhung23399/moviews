package com.example.moviews.data

import com.example.moviews.data.model.Movie

interface MovieDataSource {

    interface Local {
        fun insertMovie(movie: Movie, callback: OnLoadDataCallback<Boolean>)
        fun deleteMovie(movieId: String, callback: OnLoadDataCallback<Boolean>)
        fun getAllMovies(callback: OnLoadDataCallback<MutableList<Movie>>)
    }
}
