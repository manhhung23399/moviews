package com.example.moviews.screen.favorite

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.MovieRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class FavoritePresenterTest {
    private val view = mockk<FavoriteContract.View>(relaxed = true)
    private val repository = mockk<MovieRepository>()
    private val favoritePresenter = FavoritePresenter(view, repository)
    private val callbackMovie = slot<OnLoadDataCallback<MutableList<Movie>>>()
    private val callbackDelete = slot<OnLoadDataCallback<Boolean>>()

    @Test
    fun `getFavoriteMovies callback return onSuccess`() {
        val movies = mutableListOf<Movie>()
        every {
            repository.getFavoriteMovies(capture(callbackMovie))
        } answers {
            callbackMovie.captured.onSuccess(movies)
        }
        favoritePresenter.getFavoriteMovies()
        verify {
            view.showFavoriteMovies(movies)
        }
    }

    @Test
    fun `getFavoriteMovies callback return onError exception null`() {
        val exception: Exception? = null
        every {
            repository.getFavoriteMovies(capture(callbackMovie))
        } answers {
            callbackMovie.captured.onError(exception)
        }
        favoritePresenter.getFavoriteMovies()
        verify {
            view.showError(exception)
        }
    }

    @Test
    fun `getFavoriteMovies callback return onError exception not null`() {
        val exception = Exception()
        every {
            repository.getFavoriteMovies(capture(callbackMovie))
        } answers {
            callbackMovie.captured.onError(exception)
        }
        favoritePresenter.getFavoriteMovies()
        verify {
            view.showError(exception)
        }
    }

    @Test
    fun `deleteMovies callback return onSuccess`() {
        every {
            repository.deleteMovies("", capture(callbackDelete))
        } answers {
            callbackDelete.captured.onSuccess(true)
        }
        favoritePresenter.deleteMovies("")
    }

    @Test
    fun `deleteMovies callback return onError exception null`() {
        val exception: Exception? = null
        every {
            repository.deleteMovies("", capture(callbackDelete))
        } answers {
            callbackDelete.captured.onError(exception)
        }
        favoritePresenter.deleteMovies("")
        verify {
            view.showError(exception)
        }
    }

    @Test
    fun `deleteMovies callback return onError exception not null`() {
        val exception = Exception()
        every {
            repository.deleteMovies("", capture(callbackDelete))
        } answers {
            callbackDelete.captured.onError(exception)
        }
        favoritePresenter.deleteMovies("")
        verify {
            view.showError(exception)
        }
    }
}
