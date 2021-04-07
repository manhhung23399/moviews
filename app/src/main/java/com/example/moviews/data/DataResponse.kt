package com.example.moviews.data

import com.example.moviews.data.model.CastDetail
import com.example.moviews.data.model.CompanyDetail
import com.example.moviews.data.model.MovieDetail
import org.json.JSONObject

class DataResponse {
    fun parseMovieDetailResponse(jsonData: String) =
        MovieDetail(JSONObject(jsonData))

    fun parseCastDetailResponse(jsonData: String) =
        CastDetail(JSONObject(jsonData))

    fun parseCompanyDetailResponse(jsonData: String) =
        CompanyDetail(JSONObject(jsonData))
}
