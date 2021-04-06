package com.example.moviews.data.model

import org.json.JSONObject

data class Cast(
    val id: Int,
    val name: String,
    val profilePath: String
) {
    constructor(jsonObject: JSONObject) : this(
        id = jsonObject.getInt(CAST_ID),
        name = jsonObject.getString(CAST_NAME),
        profilePath = jsonObject.getString(CAST_PROFILE_PATH)
    )

    companion object {
        const val CREDITS = "credits"
        const val CAST = "cast"
        const val CAST_ID = "id"
        const val CAST_NAME = "name"
        const val CAST_PROFILE_PATH = "profile_path"
    }
}
