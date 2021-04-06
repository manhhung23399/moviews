package com.example.moviews.screen.search


import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Genre
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.SearchRepository
import com.example.moviews.utils.Constant

class SearchPresenter(
    private val view: SearchContract.View,
    private val repository: SearchRepository
) : SearchContract.Presenter {

    override fun getGenres() {
        repository.getGenres(object : OnLoadDataCallback<MutableList<Genre>> {
            override fun onSuccess(data: MutableList<Genre>) {
                view.showGenres(data)
            }

            override fun onError(e: Exception?) {
                view.showError(e)
            }
        })
    }

    override fun getMovies(type: String) {
        repository.getTopSearches(object : OnLoadDataCallback<MutableList<Movie>> {
            override fun onSuccess(data: MutableList<Movie>) {
                view.showTopSearch(data)
            }

            override fun onError(e: Exception?) {
                view.showError(e)
            }
        })
    }

    override fun searchMovie(name: String) {
        repository.searchMovie(name, object : OnLoadDataCallback<MutableList<Movie>> {
            override fun onSuccess(data: MutableList<Movie>) {
                view.showFoundedMovie(data)
            }

            override fun onError(e: Exception?) {
                view.showError(e)
            }
        })
    }

    override fun onStart() {
        getGenres()
        getMovies(Constant.BASE_TOP_RATE)
    }
}
