package com.example.moviews.screen.genres

import com.example.moviews.base.BasePresenter
import com.example.moviews.data.model.Movie

interface GenresContract {
    interface View {
        fun showMovies(movies: MutableList<Movie>)
        fun showError(message: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : BasePresenter<View> {
        fun getMovies(idGenre: Int)
    }
}
