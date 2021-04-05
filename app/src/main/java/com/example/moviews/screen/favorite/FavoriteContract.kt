package com.example.moviews.screen.favorite

import com.example.moviews.base.BasePresenter
import com.example.moviews.data.model.Movie
import java.lang.Exception

interface FavoriteContract {
    interface View {
        fun showFavoriteMovies(favoriteMovies: MutableList<Movie>)
        fun showError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getFavoriteMovies()
        fun deleteMovies(idMovie: String)
    }
}
