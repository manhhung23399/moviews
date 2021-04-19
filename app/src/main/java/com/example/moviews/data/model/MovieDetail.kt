package com.example.moviews.data.model

import com.example.moviews.utils.Constant
import com.example.moviews.utils.parseJsonToObject
import org.json.JSONObject

data class MovieDetail(
    val movies: Movie,
    val genres: MutableList<Genre>,
    val companies: MutableList<Company>,
    val casts: MutableList<Cast>,
    val recommendations: MutableList<Movie>,
    val trailer: MutableList<Video>
) {
    constructor(jsonObject: JSONObject) : this(
        movies = Movie(jsonObject),
        genres = jsonObject
            .getString(Genre.GENRES)
            .parseJsonToObject<Genre>(),
        companies = jsonObject
            .getString(Company.COMPANY)
            .parseJsonToObject<Company>(),
        casts = jsonObject
            .getJSONObject(Cast.CREDITS)
            .getString(Cast.CAST)
            .parseJsonToObject<Cast>(),
        recommendations = jsonObject
            .getJSONObject(Constant.BASE_RECOMMEND)
            .getString(Movie.MOVIE_RESULTS)
            .parseJsonToObject<Movie>(),
        trailer = jsonObject
            .getJSONObject(Constant.BASE_VIDEO)
            .getString(Movie.MOVIE_RESULTS)
            .parseJsonToObject<Video>()
    )
}
