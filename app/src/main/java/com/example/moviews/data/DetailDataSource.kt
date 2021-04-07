package com.example.moviews.data

import com.example.moviews.data.model.CastDetail
import com.example.moviews.data.model.MovieDetail

interface DetailDataSource {
    interface Remote {
        fun getMovieDetail(idMovie: Int, callback: OnLoadDataCallback<MovieDetail>)
        fun getCastDetail(idCast: Int, callback: OnLoadDataCallback<CastDetail>)
    }
}
