package com.example.moviews.data

import com.example.moviews.data.model.MovieDetail
import org.json.JSONObject

class DataResponse {
    fun parseMovieDetailResponse(jsonData: String) =
        MovieDetail(JSONObject(jsonData))
}
