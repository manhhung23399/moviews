package com.example.moviews.data

import com.example.moviews.data.model.Movie

interface MovieDataSource {

    interface Local {
        fun insertMovie(movie: Movie, callback: OnLoadDataCallback<Boolean>)
        fun deleteMovie(movieId: String, callback: OnLoadDataCallback<Boolean>)
        fun getAllMovies(callback: OnLoadDataCallback<MutableList<Movie>>)
    }

    interface Remote {
        fun getTrendingMovies(callback: OnLoadDataCallback<MutableList<Movie>>)
        fun getUpcomingMovies(callback: OnLoadDataCallback<MutableList<Movie>>)
        fun getNowPlayingMovies(callback: OnLoadDataCallback<MutableList<Movie>>)
        fun getPopularMovies(callback: OnLoadDataCallback<MutableList<Movie>>)
        fun getTopSearchMovies(callback: OnLoadDataCallback<MutableList<Movie>>)
        fun getMovieByGenres(idGenre: Int, callback: OnLoadDataCallback<MutableList<Movie>>)
    }
}
