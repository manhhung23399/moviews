package com.example.moviews.screen.home

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.MovieRepository
import com.example.moviews.utils.Constant

class HomePresenter(
    private val view: HomeContract.View,
    private val repository: MovieRepository
) : HomeContract.Presenter {

    override fun getMovies(type: String) {
        view.showLoading()
        repository.getMovies(type, object : OnLoadDataCallback<MutableList<Movie>> {
            override fun onSuccess(data: MutableList<Movie>) {
                when (type) {
                    Constant.BASE_POPULAR -> view.showMoviesPopular(data)
                    Constant.BASE_UPCOMING -> view.showMoviesUpcoming(data)
                    Constant.BASE_NOW_PLAYING -> view.showMoviesNowPlaying(data)
                    Constant.BASE_TRENDING -> view.showMoviesTrending(data)
                }
                view.hideLoading()
            }

            override fun onError(e: Exception?) {
                view.showError(e)
                view.hideLoading()
            }
        })
    }

    override fun  onStart() {
        getMovies(Constant.BASE_TRENDING)
        getMovies(Constant.BASE_UPCOMING)
        getMovies(Constant.BASE_NOW_PLAYING)
        getMovies(Constant.BASE_POPULAR)
    }
}
