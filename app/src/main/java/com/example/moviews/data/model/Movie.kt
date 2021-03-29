package com.example.moviews.data.model

import android.content.ContentValues
import android.database.Cursor
import org.json.JSONObject

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val backdrop: String,
    val vote: Double
) {
    constructor(jsonObject: JSONObject) : this(
        id = jsonObject.getInt(MOVIE_ID),
        title = jsonObject.getString(MOVIE_TITLE),
        poster = jsonObject.getString(MOVIE_POSTER),
        backdrop = jsonObject.getString(MOVIE_BACKDROP),
        vote = jsonObject.getDouble(MOVIE_VOTE)
    )

    constructor(cursor: Cursor) : this(
        id = cursor.getInt(cursor.getColumnIndex(MOVIE_ID)),
        title = cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)),
        poster = cursor.getString(cursor.getColumnIndex(MOVIE_POSTER)),
        backdrop = cursor.getString(cursor.getColumnIndex(MOVIE_BACKDROP)),
        vote = cursor.getDouble(cursor.getColumnIndex(MOVIE_VOTE))
    )

    fun getContentValue() = ContentValues().apply {
        put(MOVIE_ID, id)
        put(MOVIE_TITLE, title)
        put(MOVIE_POSTER, poster)
        put(MOVIE_VOTE, vote)
        put(MOVIE_BACKDROP, backdrop)
    }

    companion object {
        const val MOVIE_RESULTS = "results"
        const val MOVIE_TABLE = "tb_movie"
        const val MOVIE_ID = "id"
        const val MOVIE_TITLE = "title"
        const val MOVIE_POSTER = "poster_path"
        const val MOVIE_VOTE = "vote_average"
        const val MOVIE_BACKDROP = "backdrop_path"
    }
}
