package com.example.moviews.screen.favorite

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.MovieRepository

class FavoritePresenter(
    private val view: FavoriteContract.View,
    private val repository: MovieRepository
) : FavoriteContract.Presenter {

    override fun getFavoriteMovies() {
        view.showLoading()
        repository.getFavoriteMovies(object : OnLoadDataCallback<MutableList<Movie>> {
            override fun onSuccess(data: MutableList<Movie>) {
                view.showFavoriteMovies(data)
                view.hideLoading()
            }

            override fun onError(e: Exception?) {
                view.showError(e)
                view.hideLoading()
            }
        })
    }

    override fun deleteMovies(idMovie: String) {
        repository.deleteMovies(idMovie, object : OnLoadDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
            }

            override fun onError(e: Exception?) {
                view.showError(e)
            }
        })
    }

    override fun onStart() {
        getFavoriteMovies()
    }
}
