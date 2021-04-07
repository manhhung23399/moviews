package com.example.moviews.data.remote

import android.util.Log
import com.example.moviews.data.DetailDataSource
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.MovieDetail
import com.example.moviews.data.DataResponse
import com.example.moviews.data.model.CastDetail
import com.example.moviews.utils.Constant
import com.example.moviews.utils.buildUrl

class DetailRemoteDataSource : DetailDataSource.Remote {

    override fun getMovieDetail(idMovie: Int, callback: OnLoadDataCallback<MovieDetail>) {
        val paths = listOf(Constant.BASE_MOVIE, idMovie.toString())
        val queries = mapOf(
            Constant.BASE_APPEND to "${Constant.BASE_CREDIT},${Constant.BASE_RECOMMEND}"
        )
        RemoteAsyncTask(callback) {
            val json = readApi(buildUrl(paths, queries))
            DataResponse().parseMovieDetailResponse(json)
        }.execute()
    }

    override fun getCastDetail(idCast: Int, callback: OnLoadDataCallback<CastDetail>) {
        val paths = listOf(Constant.BASE_PERSON, idCast.toString())
        val queries = mapOf(Constant.BASE_APPEND to Constant.BASE_MOVIE_CREDITS)
        RemoteAsyncTask(callback) {
            val json = readApi(buildUrl(paths, queries))
            DataResponse().parseCastDetailResponse(json)
        }.execute()
    }

    companion object {
        private var instance: DetailRemoteDataSource? = null
        fun getInstance(): DetailRemoteDataSource =
            instance ?: DetailRemoteDataSource().also { instance = it }
    }
}
