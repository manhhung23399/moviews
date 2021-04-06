package com.example.moviews.utils

import com.example.moviews.data.model.*
import org.json.JSONArray
import org.json.JSONException

inline fun <reified T> String.parseJsonToObject() = JSONArray(this).run {
    MutableList(length()) { index ->
        when (T::class) {
            Movie::class -> Movie(getJSONObject(index)) as T
            Genre::class -> Genre(getJSONObject(index)) as T
            else -> throw JSONException("Error")
        }
    }
}
