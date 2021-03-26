package com.example.moviews.screen.information

import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.screen.favorite.FavoriteFragment

class InformationFragment : BaseFragment() {

    override val layoutID: Int
        get() = R.layout.fragment_information

    override fun initComponents() {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: InformationFragment? = null
        fun newInstance(): InformationFragment = instance ?: InformationFragment()
            .also { instance = it }
    }
}
