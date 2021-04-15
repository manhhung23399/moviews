package com.example.moviews.screen.castdetail

import com.example.moviews.base.BasePresenter
import com.example.moviews.data.model.CastDetail
import com.example.moviews.data.model.Movie

interface CastDetailContract {
    interface View {
        fun showCastDetail(castDetail: CastDetail)
        fun showMovies(movies: MutableList<Movie>)
        fun showError(message: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : BasePresenter<View> {
        fun getCastDetail(idCast: Int)
    }
}
