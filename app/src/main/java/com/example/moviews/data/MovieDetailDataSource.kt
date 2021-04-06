package com.example.moviews.data

import com.example.moviews.data.model.MovieDetail

interface MovieDetailDataSource {
    interface Remote {
        fun getMovieDetail(idMovie: Int, callback: OnLoadDataCallback<MovieDetail>)
    }
}
