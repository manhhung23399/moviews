package com.example.moviews.data.model

import com.example.moviews.utils.parseJsonToObject
import org.json.JSONObject

data class CastDetail(
    val cast: Cast,
    val movies: MutableList<Movie>,
    val biography: String,
    val place: String,
    val birthday:String
) {
    constructor(jsonObject: JSONObject) : this(
        cast = Cast(jsonObject),
        movies = jsonObject.getJSONObject(CAST_DETAIL_MOVIE_CREDIT)
            .getString(Cast.CAST)
            .parseJsonToObject<Movie>(),
        biography = jsonObject.getString(CAST_DETAIL_BIOGRAPHY),
        place = jsonObject.getString(CAST_DETAIL_PLACE),
        birthday = jsonObject.getString(CAST_DETAIL_BIRTHDAY)
    )

    companion object {
        const val CAST_DETAIL_BIOGRAPHY = "biography"
        const val CAST_DETAIL_PLACE = "place_of_birth"
        const val CAST_DETAIL_MOVIE_CREDIT = "movie_credits"
        const val CAST_DETAIL_BIRTHDAY ="birthday"
    }
}
