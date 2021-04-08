package com.example.moviews.screen.genres

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.MovieRepository

class GenresPresenter(
    private val view: GenresContract.View,
    private val repository: MovieRepository
) : GenresContract.Presenter {
    override fun getMovies(idGenre: Int) {
        repository.getMovieByGenres(idGenre, object : OnLoadDataCallback<MutableList<Movie>> {
            override fun onSuccess(data: MutableList<Movie>) {
                view.showMovies(data)
            }

            override fun onError(e: Exception?) {
                view.showError(e?.message.toString())
            }
        })
    }

    override fun onStart() {
    }
}
