package com.example.moviews.data.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.moviews.data.model.Movie

class AppDatabase private constructor(
    context: Context,
    dbName: String,
    version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_MOVIE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_MOVIE_TABLE)
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "movie.db"
        private const val DB_VERSION = 1

        private const val CREATE_MOVIE_TABLE =
            "CREATE TABLE ${Movie.MOVIE_TABLE} (" +
                    "${Movie.MOVIE_ID} INTEGER PRIMARY KEY, " +
                    "${Movie.MOVIE_TITLE} TEXT, " +
                    "${Movie.MOVIE_POSTER} TEXT, " +
                    "${Movie.MOVIE_VOTE} DOUBLE)"


        private const val DROP_MOVIE_TABLE = "DROP TABLE IF EXISTS ${Movie.MOVIE_TABLE}"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = instance ?: AppDatabase(
            context,
            DB_NAME,
            DB_VERSION
        ).also { instance = it }
    }
}
