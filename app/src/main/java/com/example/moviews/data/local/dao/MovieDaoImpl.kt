package com.example.moviews.data.local.dao

import com.example.moviews.data.model.Movie
import com.example.moviews.data.local.database.AppDatabase
import com.example.moviews.data.model.Movie.Companion.MOVIE_ID
import com.example.moviews.data.model.Movie.Companion.MOVIE_TABLE

class MovieDaoImpl private constructor(database: AppDatabase) : MovieDao {

    private val writableDB = database.writableDatabase
    private val readableDB = database.readableDatabase

    override fun insertMovie(movie: Movie) =
        writableDB.insert(
            MOVIE_TABLE,
            null,
            movie.getContentValue()
        ) > 0

    override fun deleteMovie(idMovie: String) =
        writableDB.delete(
            MOVIE_TABLE,
            "$MOVIE_ID=?",
            arrayOf(idMovie)
        ) > 0

    override fun getAllMovies(): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        val cursor = readableDB.query(
            MOVIE_TABLE,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            movies.add(Movie(cursor))
        }
        return movies
    }

    companion object {
        private var instance: MovieDaoImpl? = null
        fun getInstance(data: AppDatabase): MovieDaoImpl =
            instance ?: MovieDaoImpl(data).also { instance = it }
    }
}
