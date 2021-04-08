package com.example.moviews.screen.genres

import androidx.core.os.bundleOf
import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.RepositoryUtils
import com.example.moviews.screen.genres.adapter.MovieByGenreAdapter
import com.example.moviews.screen.moviedetail.MovieDetailFragment
import com.example.moviews.utils.addFragment
import com.example.moviews.utils.showToast
import kotlinx.android.synthetic.main.fragment_genres.*

class GenresFragment() : BaseFragment(), GenresContract.View {

    private val movieByGenreAdapter = MovieByGenreAdapter(this::onClickMovie)
    private var presenter: GenresPresenter? = null
    override val layoutID: Int
        get() = R.layout.fragment_genres

    override fun initViews() {
        recyclerMovieByGenre.apply {
            setHasFixedSize(true)
            adapter = movieByGenreAdapter
        }
    }

    override fun initData() {
        context?.let {
            val movieRepository = RepositoryUtils.getMovieRepository(it)
            presenter = GenresPresenter(this, movieRepository)
        }
        arguments?.let { presenter?.getMovies(it.getInt(BUNDLE_GENRE_ID)) }
    }

    override fun initEvents() {
        imageBackGenre.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun showMovies(movies: MutableList<Movie>) {
        textGenre.text = arguments?.getString(BUNDLE_GENRE_NAME)
        movieByGenreAdapter.updateData(movies)
    }

    override fun showError(message: String) {
        showToast(message)
    }

    private fun onClickMovie(movie: Movie) {
        addFragment(MovieDetailFragment.getInstance(movie.id))
    }

    companion object {
        private const val BUNDLE_GENRE_ID = "BUNDLE_GENRE_ID"
        private const val BUNDLE_GENRE_NAME = "BUNDLE_GENRE_NAME"
        fun getInstance(idGenres: Int, name: String) = GenresFragment().apply {
            arguments = bundleOf(
                BUNDLE_GENRE_ID to idGenres,
                BUNDLE_GENRE_NAME to name
            )
        }
    }

}
