package com.example.moviews.screen.favorite

import androidx.appcompat.app.AlertDialog
import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.RepositoryUtils
import com.example.moviews.screen.favorite.adapter.FavoriteAdapter
import com.example.moviews.screen.moviedetail.MovieDetailFragment
import com.example.moviews.utils.addFragment
import com.example.moviews.utils.showToast
import kotlinx.android.synthetic.main.fragment_favorite.*
import java.lang.Exception

class FavoriteFragment : BaseFragment(), FavoriteContract.View {

    private var presenter: FavoritePresenter? = null
    private val favoriteAdapter = FavoriteAdapter(this::onClickItem, this::onClickDelete)

    override val layoutID: Int
        get() = R.layout.fragment_favorite

    override fun initViews() {
        recyclerFavorite.apply {
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    override fun initData() {
        context?.let {
            val movieRepository = RepositoryUtils.getMovieRepository(it)
            presenter = FavoritePresenter(this, movieRepository)
        }
        presenter?.onStart()
    }

    override fun initEvent() {
        initActions()
    }

    override fun showFavoriteMovies(favoriteMovies: MutableList<Movie>) {
        favoriteAdapter.updateData(favoriteMovies)
    }

    override fun showError(exception: Exception?) {
        showToast(exception.toString())
    }

    fun onClickItem(movie: Movie) {
        addFragment(MovieDetailFragment.getInstance(movie.id))
    }

    private fun onClickDelete(movie: Movie) {
        activity?.let {
            AlertDialog.Builder(it).apply {
                setMessage(R.string.message_dialog)
                    .setPositiveButton(R.string.text_yes) { _, _ ->
                        presenter?.deleteMovies(movie.id.toString())
                        presenter?.onStart()
                    }
                    .setNegativeButton(R.string.text_no) { _, _ ->
                    }
                    .create()
                    .show()
            }
        }
    }

    private fun initActions() {
        imageBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    companion object {
        private var instance: FavoriteFragment? = null
        fun newInstance(): FavoriteFragment = instance ?: FavoriteFragment()
            .also { instance = it }
    }
}
