package com.example.moviews.screen.genres

import androidx.core.os.bundleOf
import com.example.moviews.R
import com.example.moviews.base.BaseFragment

class GenresFragment() : BaseFragment() {

    override val layoutID: Int
        get() = R.layout.fragment_genres

    override fun initViews() {
    }

    override fun initData() {
    }

    override fun initEvents() {
    }

    companion object {
        private const val BUNDLE_GENRE_ID = "BUNDLE_GENRE_ID"
        fun getInstance(idGenres: Int) = GenresFragment().apply {
            arguments = bundleOf(BUNDLE_GENRE_ID to idGenres)
        }
    }
}
