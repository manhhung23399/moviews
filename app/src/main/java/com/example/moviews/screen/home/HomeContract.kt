package com.example.moviews.screen.home

import com.example.moviews.base.BasePresenter
import com.example.moviews.data.model.Movie

interface HomeContract {
    interface View {
        fun showMoviesNowPlaying(movies: MutableList<Movie>)
        fun showMoviesUpcoming(movies: MutableList<Movie>)
        fun showMoviesTrending(movies: MutableList<Movie>)
        fun showMoviesPopular(movies: MutableList<Movie>)
        fun showError(exception: Exception?)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : BasePresenter<View> {
        fun getMovies(type: String)
    }
}
