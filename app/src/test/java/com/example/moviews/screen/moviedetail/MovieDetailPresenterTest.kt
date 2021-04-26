package com.example.moviews.screen.moviedetail

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.*
import com.example.moviews.repository.DetailRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class MovieDetailPresenterTest {
    private val view = mockk<MovieDetailContract.View>(relaxed = true)
    private val repository = mockk<DetailRepository>()
    private val movieDetailPresenter = MovieDetailPresenter(view, repository)
    private val callback = slot<OnLoadDataCallback<MovieDetail>>()
    private val callbackInsert = slot<OnLoadDataCallback<Boolean>>()

    @Test
    fun `getMovieDetail call back return onSuccess`() {
        val movie = Movie(0, "", "", "", 0.0, "", "")
        val genres = mutableListOf<Genre>()
        val companies = mutableListOf<Company>()
        val casts = mutableListOf<Cast>()
        val recommends = mutableListOf<Movie>()
        val movieDetail = MovieDetail(movie, genres, companies, casts, recommends)
        every {
            repository.getMovieDetail(0, capture(callback))
        } answers {
            callback.captured.onSuccess(movieDetail)
        }
        movieDetailPresenter.getMovieDetail(0)
        verify {
            view.run {
                showCasts(movieDetail.casts)
                showCompanies(movieDetail.companies)
                showGenres(movieDetail.genres)
                showMovie(movieDetail.movies)
                showRecommendations(movieDetail.recommendations)
            }
        }
    }

    @Test
    fun `getMovieDetail call back return onError exception not null`() {
        val exception = Exception()
        every {
            repository.getMovieDetail(0, capture(callback))
        } answers {
            callback.captured.onError(exception)
        }
        movieDetailPresenter.getMovieDetail(0)
        verify {
            view.showError(exception.message.toString())
        }
    }

    @Test
    fun `getMovieDetail call back return onError exception null`() {
        val exception: Exception? = null
        every {
            repository.getMovieDetail(0, capture(callback))
        } answers {
            callback.captured.onError(exception)
        }
        movieDetailPresenter.getMovieDetail(0)
        verify {
            view.showError(exception?.message.toString())
        }
    }

    @Test
    fun `insertMovie callback return onSuccess`() {
        val movie = Movie(0, "", "", "", 0.0, "", "")
        every {
            repository.insertMovie(movie, capture(callbackInsert))
        } answers {
            callbackInsert.captured.onSuccess(true)
        }
        movieDetailPresenter.insertMovie(movie)
    }

    @Test
    fun `insertMovie call back return onError exception not null`() {
        val movie = Movie(0, "", "", "", 0.0, "", "")
        val exception = Exception()
        every {
            repository.insertMovie(movie, capture(callbackInsert))
        } answers {
            callbackInsert.captured.onError(exception)
        }
        movieDetailPresenter.insertMovie(movie)
    }

    @Test
    fun `insertMovie call back return onError exception null`() {
        val movie = Movie(0, "", "", "", 0.0, "", "")
        val exception: Exception? = null
        every {
            repository.insertMovie(movie, capture(callbackInsert))
        } answers {
            callbackInsert.captured.onError(exception)
        }
        movieDetailPresenter.insertMovie(movie)
    }
}
