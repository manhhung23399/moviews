package com.example.moviews.data.model

import com.example.moviews.utils.Constant
import org.json.JSONObject

data class Video(
    val key: String
) {
    constructor(jsonObject: JSONObject) : this(
        key = jsonObject
            .getString(VIDEO_KEY)
    )

    companion object {
        const val VIDEO_KEY = "key"
    }
}