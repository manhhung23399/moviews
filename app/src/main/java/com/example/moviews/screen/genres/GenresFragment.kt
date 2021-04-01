package com.example.moviews.screen.genres

import com.example.moviews.R
import com.example.moviews.base.BaseFragment

class GenresFragment(idGenres: Int) : BaseFragment() {

    override val layoutID: Int
        get() = R.layout.fragment_genres

    override fun initViews() {
    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    companion object {
        fun getInstance(idGenres: Int): GenresFragment = GenresFragment(idGenres)
    }
}
