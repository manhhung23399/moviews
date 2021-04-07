package com.example.moviews.screen.moviedetail

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.data.model.MovieDetail
import com.example.moviews.repository.DetailRepository

class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
    private val repository: DetailRepository,
) : MovieDetailContract.Presenter {

    override fun getMovieDetail(idMovie: Int) {
        repository.getMovieDetail(idMovie, object : OnLoadDataCallback<MovieDetail> {
            override fun onSuccess(data: MovieDetail) {
                view.run {
                    showMovie(data.movies)
                    showGenres(data.genres)
                    showCasts(data.casts)
                    showCompanies(data.companies)
                    showRecommendations(data.recommendations)
                }
            }

            override fun onError(e: Exception?) {
                view.showError(e?.message.toString())
            }
        })
    }

    override fun insertMovie(movie: Movie) {
        repository.insertMovie(movie, object : OnLoadDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
            }

            override fun onError(e: Exception?) {
            }
        })
    }

    override fun onStart() {
    }

}
