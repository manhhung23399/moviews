package com.example.moviews.screen.home

import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.data.model.Movie
import com.example.moviews.screen.home.adapter.MovieAdapter
import com.example.moviews.screen.home.adapter.ViewPagerAdapter
import com.example.moviews.repository.RepositoryUtils
import com.example.moviews.screen.moviedetail.MovieDetailFragment
import com.example.moviews.utils.addFragment
import com.example.moviews.utils.showToast
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.viewPagerBanner

class HomeFragment : BaseFragment(), HomeContract.View {
    private var presenter: HomePresenter? = null
    private val trendingAdapter = MovieAdapter(this::onClickItemRecycler)
    private val upcomingAdapter = MovieAdapter(this::onClickItemRecycler)
    private val nowPlayingAdapter = MovieAdapter(this::onClickItemRecycler)
    private val viewPageAdapter = ViewPagerAdapter(this::onClickItemViewPager)
    override val layoutID: Int
        get() = R.layout.fragment_home

    override fun initViews() {
        recyclerTrendingNow.apply {
            setHasFixedSize(true)
            adapter = trendingAdapter
        }
        recyclerNowPlaying.apply {
            setHasFixedSize(true)
            adapter = nowPlayingAdapter
        }
        recyclerUpcoming.apply {
            setHasFixedSize(true)
            adapter = upcomingAdapter
        }
        viewPagerBanner.adapter = viewPageAdapter
        tabIndicator.setupWithViewPager(
            viewPagerBanner,
            true
        )
    }

    override fun initData() {
        context?.let {
            val movieRepository = RepositoryUtils.getMovieRepository(it)
            presenter = HomePresenter(this, movieRepository)
        }
        presenter?.onStart()
    }

    override fun initEvents() {
    }

    override fun showMoviesNowPlaying(movies: MutableList<Movie>) {
        nowPlayingAdapter.updateData(movies)
    }

    override fun showMoviesUpcoming(movies: MutableList<Movie>) {
        upcomingAdapter.updateData(movies)
    }

    override fun showMoviesTrending(movies: MutableList<Movie>) {
        trendingAdapter.updateData(movies)
    }

    override fun showMoviesPopular(movies: MutableList<Movie>) {
        viewPageAdapter.updateData(movies)
    }

    override fun showError(exception: Exception?) {
        showToast(exception?.message.toString())
    }

    private fun onClickItemRecycler(movie: Movie) {
        addFragment(MovieDetailFragment.getInstance(movie.id))
    }

    private fun onClickItemViewPager(movie: Movie) {
        addFragment(MovieDetailFragment.getInstance(movie.id))
    }

    companion object {
        private var instance: HomeFragment? = null
        fun newInstance(): HomeFragment = instance ?: HomeFragment()
            .also { instance = it }
    }
}
