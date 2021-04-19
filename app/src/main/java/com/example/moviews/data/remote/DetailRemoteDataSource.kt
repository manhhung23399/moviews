package com.example.moviews.data.remote

import com.example.moviews.data.DetailDataSource
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.MovieDetail
import com.example.moviews.data.DataResponse
import com.example.moviews.data.model.CastDetail
import com.example.moviews.data.model.CompanyDetail
import com.example.moviews.utils.Constant
import com.example.moviews.utils.buildUrl

class DetailRemoteDataSource : DetailDataSource.Remote {

    override fun getMovieDetail(idMovie: Int, callback: OnLoadDataCallback<MovieDetail>) {
        val paths = listOf(Constant.BASE_MOVIE, idMovie.toString())
        val queries = mapOf(
            Constant.BASE_APPEND to "${Constant.BASE_CREDIT},${Constant.BASE_RECOMMEND},${Constant.BASE_VIDEO}"
        )
        RemoteAsyncTask(callback) {
            val json = readApi(buildUrl(paths, queries))
            DataResponse().parseMovieDetailResponse(json)
        }.execute()
    }

    override fun getCastDetail(idCast: Int, callback: OnLoadDataCallback<CastDetail>) {
        val paths = listOf(Constant.BASE_PERSON, idCast.toString())
        val queries = mapOf(Constant.BASE_APPEND to BASE_MOVIE_CREDITS)
        RemoteAsyncTask(callback) {
            val json = readApi(buildUrl(paths, queries))
            DataResponse().parseCastDetailResponse(json)
        }.execute()
    }

    override fun getCompanyDetail(idCompany: Int, callback: OnLoadDataCallback<CompanyDetail>) {
        val paths = listOf(BASE_COMPANY, idCompany.toString())
        RemoteAsyncTask(callback) {
            val json = readApi(buildUrl(paths))
            DataResponse().parseCompanyDetailResponse(json)
        }.execute()
    }

    companion object {
        const val BASE_MOVIE_CREDITS = "movie_credits"
        const val BASE_COMPANY = "company"
        private var instance: DetailRemoteDataSource? = null
        fun getInstance(): DetailRemoteDataSource =
            instance ?: DetailRemoteDataSource().also { instance = it }
    }
}
