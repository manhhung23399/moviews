package com.example.moviews.utils

import com.example.moviews.data.model.*
import org.json.JSONArray
import org.json.JSONException

inline fun <reified T> String.parseJsonToObject() = JSONArray(this).run {
    MutableList(length()) { index ->
        when (T::class) {
            Movie::class -> Movie(getJSONObject(index)) as T
            Cast::class -> Cast(getJSONObject(index)) as T
            Company::class -> Company(getJSONObject(index)) as T
            Genre::class -> Genre(getJSONObject(index)) as T
            Video::class -> Video(getJSONObject(index)) as T
            else -> throw JSONException("Error")
        }
    }
}
