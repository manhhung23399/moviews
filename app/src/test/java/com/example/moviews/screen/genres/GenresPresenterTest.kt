package com.example.moviews.screen.genres

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.MovieRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class GenresPresenterTest {

    private val view = mockk<GenresContract.View>(relaxed = true)
    private val repository = mockk<MovieRepository>()
    private val genresPresenter = GenresPresenter(view, repository)
    private val callback = slot<OnLoadDataCallback<MutableList<Movie>>>()

    @Test
    fun `getMovies callback return onSuccess`() {
        val movies = mutableListOf<Movie>()
        every {
            repository.getMovieByGenres(0, capture(callback))
        } answers {
            callback.captured.onSuccess(movies)
        }
        genresPresenter.getMovies(0)
        verify {
            view.showMovies(movies)
        }
    }

    @Test
    fun `getMovies callback return onError exception not null`() {
        val exception = Exception()
        every {
            repository.getMovieByGenres(0, capture(callback))
        } answers {
            callback.captured.onError(exception)
        }
        genresPresenter.getMovies(0)
        verify {
            view.showError(exception.message.toString())
        }
    }

    @Test
    fun `getMovies callback return onError exception null`() {
        val exception: Exception? = null
        every {
            repository.getMovieByGenres(0, capture(callback))
        } answers {
            callback.captured.onError(exception)
        }
        genresPresenter.getMovies(0)
        verify {
            view.showError(exception?.message.toString())
        }
    }
}
