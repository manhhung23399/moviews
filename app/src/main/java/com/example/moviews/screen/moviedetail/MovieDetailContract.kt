package com.example.moviews.screen.moviedetail

import com.example.moviews.base.BasePresenter
import com.example.moviews.data.model.Cast
import com.example.moviews.data.model.Company
import com.example.moviews.data.model.Genre
import com.example.moviews.data.model.Movie

interface MovieDetailContract {
    interface View {
        fun showMovie(movie: Movie)
        fun showGenres(genres: MutableList<Genre>)
        fun showCasts(casts: MutableList<Cast>)
        fun showCompanies(companies: MutableList<Company>)
        fun showRecommendations(recommendations: MutableList<Movie>)
        fun showError(message:String)
        fun showFavoriteMovie(movies: MutableList<Movie>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : BasePresenter<View> {
        fun getMovieDetail(idMovie: Int)
        fun insertMovie(movie: Movie)
        fun getFavoriteMovie()
        fun deleteFavoriteMovie(idMovie: Int)
    }
}
