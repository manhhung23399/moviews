package com.example.moviews.screen.search

import android.app.Activity
import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.data.model.Genre
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.RepositoryUtils
import com.example.moviews.screen.genres.GenresFragment
import com.example.moviews.screen.moviedetail.MovieDetailFragment
import com.example.moviews.screen.search.adapter.GenresAdapter
import com.example.moviews.screen.search.adapter.TopSearchAdapter
import com.example.moviews.utils.LoadingDialog
import com.example.moviews.utils.addFragment
import com.example.moviews.utils.showToast
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment(), SearchContract.View {
    private var loadingDialog: LoadingDialog? = null
    private var presenter: SearchPresenter? = null
    private val topSearchAdapter = TopSearchAdapter(this::onClickItemRecycler)
    private val genresAdapter = GenresAdapter(this::onClickGenres)
    override val layoutID: Int
        get() = R.layout.fragment_search

    override fun initViews() {
        recyclerViewTopSearch.apply {
            setHasFixedSize(true)
            adapter = topSearchAdapter
        }
        recyclerGenres.apply {
            setHasFixedSize(true)
            adapter = genresAdapter
        }
        initDialog()
    }

    override fun initData() {
        context?.let {
            val searchRepository = RepositoryUtils.getSearchRepository()
            presenter = SearchPresenter(this, searchRepository)
        }
        presenter?.onStart()
    }

    override fun initEvents() {
        initActions()

    }

    override fun showGenres(genres: MutableList<Genre>) {
        genresAdapter.updateData(genres)
    }

    override fun showTopSearch(movies: MutableList<Movie>) {
        topSearchAdapter.updateData(movies)
    }

    override fun showFoundedMovie(movies: MutableList<Movie>) {
        topSearchAdapter.updateData(movies)
    }

    override fun showError(exception: Exception?) {
        showToast(exception?.message.toString())
    }

    override fun showLoading() {
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    private fun onClickItemRecycler(movie: Movie) {
        addFragment(MovieDetailFragment.getInstance(movie.id))
    }

    private fun onClickGenres(genre: Genre) {
        addFragment(GenresFragment.getInstance(genre.id, genre.name))
    }

    private fun initActions() {
        editSearch.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                val name = editSearch.text.toString()
                presenter?.searchMovie(name)
                val inputMethodManager =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
            }
            return@setOnKeyListener true
        }
    }

    companion object {
        private var instance: SearchFragment? = null
        fun newInstance(): SearchFragment = instance ?: SearchFragment()
            .also { instance = it }
    }
}
