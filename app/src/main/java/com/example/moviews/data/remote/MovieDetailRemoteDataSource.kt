package com.example.moviews.data.remote

import android.net.Uri
import com.example.moviews.BuildConfig
import com.example.moviews.data.MovieDetailDataSource
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.MovieDetail
import com.example.moviews.data.DataResponse
import com.example.moviews.utils.Constant

class MovieDetailRemoteDataSource : MovieDetailDataSource.Remote {
    private fun buildUrl(idMovie: Int) = Uri.Builder()
        .scheme(Constant.BASE_HTTPS)
        .authority(Constant.BASE_AUTHORITY)
        .appendPath(Constant.BASE_VERSION)
        .appendPath(Constant.BASE_MOVIE)
        .appendPath(idMovie.toString())
        .appendQueryParameter(Constant.BASE_KEY, BuildConfig.API_KEY)
        .appendQueryParameter(
            Constant.BASE_APPEND,
            "${Constant.BASE_CREDIT},${Constant.BASE_RECOMMEND}"
        )
        .toString()

    override fun getMovieDetail(idMovie: Int, callback: OnLoadDataCallback<MovieDetail>) {

        RemoteAsyncTask(callback) {
            DataResponse().parseMovieDetailResponse(readApi(buildUrl(idMovie)))
        }.execute()
    }

    companion object {
        private var instance: MovieDetailRemoteDataSource? = null
        fun getInstance(): MovieDetailRemoteDataSource =
            instance ?: MovieDetailRemoteDataSource().also { instance = it }
    }
}
