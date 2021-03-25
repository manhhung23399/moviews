package com.example.moviews.screen.home

import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.screen.favorite.FavoriteFragment

class HomeFragment : BaseFragment() {

    override val layoutID: Int
        get() = R.layout.fragment_home

    override fun initComponents() {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: HomeFragment? = null
        fun newInstance(): HomeFragment = instance ?: HomeFragment()
            .also { instance = it }
    }
}
