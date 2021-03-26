package com.example.moviews.screen.favorite

import com.example.moviews.R
import com.example.moviews.base.BaseFragment

class FavoriteFragment : BaseFragment() {

    override val layoutID: Int
        get() = R.layout.fragment_favorite

    override fun initComponents() {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: FavoriteFragment? = null
        fun newInstance(): FavoriteFragment = instance ?: FavoriteFragment()
            .also { instance = it }
    }
}
