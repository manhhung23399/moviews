package com.example.moviews.data.model

import org.json.JSONObject

data class Genre(
    val id: Int,
    val name: String
) {
    constructor(jsonObject: JSONObject) : this(
        id = jsonObject.getInt(GENRES_ID),
        name = jsonObject.getString(GENRES_NAME)
    )

    companion object {
        const val GENRES = "genres"
        const val GENRES_ID = "id"
        const val GENRES_NAME = "name"
    }
}
