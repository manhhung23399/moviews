package com.example.moviews.screen.moviedetail

import com.example.moviews.R
import com.example.moviews.base.BaseFragment

class MovieDetailFragment(idMovie: Int) : BaseFragment() {
    override val layoutID: Int
        get() = R.layout.fragment_movie_detail

    override fun initViews() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    companion object {
        fun getInstance(idMovie: Int) = MovieDetailFragment(idMovie)
    }
}
