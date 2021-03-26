package com.example.moviews.data.model

import android.content.ContentValues
import android.database.Cursor

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val vote: Double
) {
    constructor(cursor: Cursor) : this(
        id = cursor.getInt(cursor.getColumnIndex(MOVIE_ID)),
        title = cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)),
        poster = cursor.getString(cursor.getColumnIndex(MOVIE_POSTER)),
        vote = cursor.getDouble(cursor.getColumnIndex(MOVIE_VOTE))
    )

    fun getContentValue() = ContentValues().apply {
        put(MOVIE_ID, id)
        put(MOVIE_TITLE, title)
        put(MOVIE_POSTER, poster)
        put(MOVIE_VOTE, vote)
    }

    companion object {
        const val MOVIE_TABLE = "tb_movie"
        const val MOVIE_ID = "id"
        const val MOVIE_TITLE = "title"
        const val MOVIE_POSTER = "poster"
        const val MOVIE_VOTE = "vote"
    }
}
