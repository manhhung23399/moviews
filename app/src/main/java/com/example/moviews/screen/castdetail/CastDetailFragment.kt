package com.example.moviews.screen.castdetail

import androidx.core.os.bundleOf
import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.data.model.CastDetail
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.RepositoryUtils
import com.example.moviews.screen.home.adapter.MovieAdapter
import com.example.moviews.screen.moviedetail.MovieDetailFragment
import com.example.moviews.utils.Constant
import com.example.moviews.utils.addFragment
import com.example.moviews.utils.loadImage
import com.example.moviews.utils.showToast
import kotlinx.android.synthetic.main.fragment_cast_detail.*

class CastDetailFragment : BaseFragment(), CastDetailContract.View {
    private val movieAdapter = MovieAdapter(this::onClickMovies)
    private var presenter: CastDetailPresenter? = null
    override val layoutID: Int
        get() = R.layout.fragment_cast_detail

    override fun initViews() {
        recyclerKnowFor.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun initData() {
        context?.let {
            val castDetailRepository = RepositoryUtils.getDetailRepository(it)
            presenter = CastDetailPresenter(this, castDetailRepository)
        }
        arguments?.let { presenter?.getCastDetail(it.getInt(BUNDLE_CAST_ID)) }
    }

    override fun initEvent() {
        imageBackCastDetail.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun showCastDetail(castDetail: CastDetail) {
        imageCastDetail.loadImage(Constant.BASE_URL_IMAGE + castDetail.cast.profilePath)
        textNameCastDetail.append(castDetail.cast.name)
        textBirthDay.append(castDetail.birthday)
        textPlace.append(castDetail.place)
        textBiography.text = castDetail.biography
    }

    override fun showMovies(movies: MutableList<Movie>) {
        movieAdapter.updateData(movies)
    }

    override fun showError(message: String) {
        showToast(message)
    }

    private fun onClickMovies(movie: Movie) {
        addFragment(MovieDetailFragment.getInstance(movie.id))
    }

    companion object {
        private const val BUNDLE_CAST_ID = "BUNDLE_CAST_ID"
        fun getInstance(idCast: Int) = CastDetailFragment().apply {
            arguments = bundleOf(BUNDLE_CAST_ID to idCast)
        }
    }
}
