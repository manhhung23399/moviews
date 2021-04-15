package com.example.moviews.screen.search

import com.example.moviews.base.BasePresenter
import com.example.moviews.data.model.Genre
import com.example.moviews.data.model.Movie

interface SearchContract {
    interface View{
        fun showGenres(genres:MutableList<Genre>)
        fun showTopSearch(movies:MutableList<Movie>)
        fun showFoundedMovie(movies: MutableList<Movie>)
        fun showError(exception: Exception?)
        fun showLoading()
        fun hideLoading()
    }
    interface Presenter:BasePresenter<View>{
        fun getGenres()
        fun getMovies(type:String)
        fun searchMovie(name:String)
    }
}
