package com.example.moviews.screen.moviedetail

import androidx.core.os.bundleOf
import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.data.model.Cast
import com.example.moviews.data.model.Company
import com.example.moviews.data.model.Genre
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.RepositoryUtils
import com.example.moviews.screen.castdetail.CastDetailFragment
import com.example.moviews.screen.companydetail.CompanyDetailFragment
import com.example.moviews.screen.genres.GenresFragment
import com.example.moviews.screen.moviedetail.adapter.CastAdapter
import com.example.moviews.screen.moviedetail.adapter.CompanyAdapter
import com.example.moviews.screen.moviedetail.adapter.RecommendAdapter
import com.example.moviews.screen.search.adapter.GenresAdapter
import com.example.moviews.utils.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie_detail.imageBack

class MovieDetailFragment : BaseFragment(), MovieDetailContract.View {
    private var checkFavorite:Boolean? = null
    private var loadingDialog: LoadingDialog? = null
    private var movie: Movie? = null
    private val recommendAdapter = RecommendAdapter(this::onClickRecommend)
    private val companyAdapter = CompanyAdapter(this::onClickCompanies)
    private val castAdapter = CastAdapter(this::onClickCasts)
    private val genresAdapter = GenresAdapter(this::onClickGenres)
    private var presenter: MovieDetailPresenter? = null
    override val layoutID: Int
        get() = R.layout.fragment_movie_detail

    override fun initViews() {
        recyclerGenres.apply {
            setHasFixedSize(true)
            adapter = genresAdapter
        }
        recyclerCast.apply {
            setHasFixedSize(true)
            adapter = castAdapter
        }
        recyclerCompany.apply {
            setHasFixedSize(true)
            adapter = companyAdapter
        }
        recyclerRecommend.apply {
            setHasFixedSize(true)
            adapter = recommendAdapter
        }
        initDialog()
    }

    override fun initData() {
        context?.let {
            val movieDetailRepository = RepositoryUtils.getDetailRepository(it)
            presenter = MovieDetailPresenter(this, movieDetailRepository)
        }
        arguments?.let { presenter?.getMovieDetail(it.getInt(BUNDLE_MOVIE_ID)) }
        presenter?.getFavoriteMovie()
    }

    override fun initEvents() {
        imageBack.setOnClickListener {
            activity?.onBackPressed()
        }
        imageFavoriteDetail.setOnClickListener {
            checkFavorite = if (checkFavorite == true){
                arguments?.getInt(BUNDLE_MOVIE_ID)?.let { presenter?.deleteFavoriteMovie(it) }
                imageFavoriteDetail.setImageResource(R.drawable.ic_favorite)
                false
            }else{
                movie?.let { presenter?.insertMovie(it) }
                imageFavoriteDetail.setImageResource(R.drawable.ic_favorite_red)
                true
            }
        }
    }

    override fun showMovie(movie: Movie) {
        if (imageBackdrop != null) {
            imageBackdrop.loadImage(Constant.BASE_URL_IMAGE + movie.backdrop)
        }
        if (imagePosterDetail != null) {
            imagePosterDetail.loadImage(Constant.BASE_URL_IMAGE + movie.poster)
        }
        textVoteDetail.text = movie.vote.toString()
        textOverview.text = movie.overview
        textDate.text = movie.date
        this.movie = movie
    }

    override fun showGenres(genres: MutableList<Genre>) {
        genresAdapter.updateData(genres)
    }

    override fun showCasts(casts: MutableList<Cast>) {
        castAdapter.updateData(casts)
    }

    override fun showCompanies(companies: MutableList<Company>) {
        companyAdapter.updateData(companies)
    }

    override fun showRecommendations(recommendations: MutableList<Movie>) {
        recommendAdapter.updateData(recommendations)
    }

    override fun showError(message: String) {
        showToast(message)
    }

    override fun showFavoriteMovie(movies: MutableList<Movie>) {
        movies.forEach {
            if (arguments?.getInt(BUNDLE_MOVIE_ID) == it.id) {
                imageFavoriteDetail.setImageResource(R.drawable.ic_favorite_red)
                checkFavorite = true
            }
        }
    }

    override fun showLoading() {
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }

    private fun onClickGenres(genre: Genre) {
        addFragment(GenresFragment.getInstance(genre.id, genre.name))
    }

    private fun onClickCasts(cast: Cast) {
        addFragment(CastDetailFragment.getInstance(cast.id))
    }

    private fun onClickCompanies(company: Company) {
        addFragment(CompanyDetailFragment.getInstance(company.id))
    }

    private fun onClickRecommend(movie: Movie) {
        addFragment(getInstance(movie.id))
    }

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    companion object {
        private const val BUNDLE_MOVIE_ID = "BUNDLE_MOVIE_ID"
        fun getInstance(idMovie: Int) = MovieDetailFragment().apply {
            arguments = bundleOf(BUNDLE_MOVIE_ID to idMovie)
        }
    }
}
