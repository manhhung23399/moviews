package com.example.moviews.data.local.dao

import com.example.moviews.data.model.Movie

interface MovieDao {
    fun insertMovie(movie: Movie): Boolean
    fun deleteMovie(idMovie: String): Boolean
    fun getAllMovies(): MutableList<Movie>
}
